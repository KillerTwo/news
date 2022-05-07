/*
*  Copyright 2019-2020 Zheng Jie
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*  http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/
package org.el.modules.news.service.impl;

import org.el.modules.news.domain.NewsArticle;
import lombok.RequiredArgsConstructor;
import org.el.modules.news.repository.NewsArticleRepository;
import org.el.modules.news.service.NewsArticleService;
import org.el.modules.news.service.dto.NewsArticleDto;
import org.el.modules.news.service.dto.NewsArticleQueryCriteria;
import org.el.modules.news.service.mapstruct.NewsArticleMapper;
import org.el.utils.FileUtil;
import org.el.utils.PageUtil;
import org.el.utils.QueryHelp;
import org.el.utils.ValidationUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
* @website https://el-admin.vip
* @description 服务实现
* @author qingtian@gmail.com
* @date 2022-05-08
**/
@Service
@RequiredArgsConstructor
public class NewsArticleServiceImpl implements NewsArticleService {

    private final NewsArticleRepository newsArticleRepository;
    private final NewsArticleMapper newsArticleMapper;

    @Override
    public Map<String,Object> queryAll(NewsArticleQueryCriteria criteria, Pageable pageable){
        Page<NewsArticle> page = newsArticleRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(newsArticleMapper::toDto));
    }

    @Override
    public List<NewsArticleDto> queryAll(NewsArticleQueryCriteria criteria){
        return newsArticleMapper.toDto(newsArticleRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public NewsArticleDto findById(Integer id) {
        NewsArticle newsArticle = newsArticleRepository.findById(id).orElseGet(NewsArticle::new);
        ValidationUtil.isNull(newsArticle.getId(),"NewsArticle","id",id);
        return newsArticleMapper.toDto(newsArticle);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public NewsArticleDto create(NewsArticle resources) {
        return newsArticleMapper.toDto(newsArticleRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(NewsArticle resources) {
        NewsArticle newsArticle = newsArticleRepository.findById(resources.getId()).orElseGet(NewsArticle::new);
        ValidationUtil.isNull( newsArticle.getId(),"NewsArticle","id",resources.getId());
        newsArticle.copy(resources);
        newsArticleRepository.save(newsArticle);
    }

    @Override
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            newsArticleRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<NewsArticleDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (NewsArticleDto newsArticle : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("创建人", newsArticle.getCreateBy());
            map.put("创建时间", newsArticle.getCreateTime());
            map.put("更新人", newsArticle.getUpdateBy());
            map.put("更新时间", newsArticle.getUpdateTime());
            map.put("标题", newsArticle.getTitle());
            map.put("摘要", newsArticle.getExcerpt());
            map.put("新闻分类ID", newsArticle.getCategory());
            map.put("封面图片", newsArticle.getImg());
            map.put("正文", newsArticle.getContent());
            map.put("作者", newsArticle.getAuthor());
            map.put("阅读量", newsArticle.getViews());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}