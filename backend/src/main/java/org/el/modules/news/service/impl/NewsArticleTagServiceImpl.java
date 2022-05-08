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

import org.el.modules.news.domain.NewsArticleTag;
import lombok.RequiredArgsConstructor;
import org.el.modules.news.repository.NewsArticleTagRepository;
import org.el.modules.news.service.NewsArticleTagService;
import org.el.modules.news.service.dto.NewsArticleTagDto;
import org.el.modules.news.service.dto.NewsArticleTagQueryCriteria;
import org.el.modules.news.service.mapstruct.NewsArticleTagMapper;
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
public class NewsArticleTagServiceImpl implements NewsArticleTagService {

    private final NewsArticleTagRepository newsArticleTagRepository;
    private final NewsArticleTagMapper newsArticleTagMapper;

    @Override
    public Map<String,Object> queryAll(NewsArticleTagQueryCriteria criteria, Pageable pageable){
        Page<NewsArticleTag> page = newsArticleTagRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(newsArticleTagMapper::toDto));
    }

    @Override
    public List<NewsArticleTagDto> queryAll(NewsArticleTagQueryCriteria criteria){
        return newsArticleTagMapper.toDto(newsArticleTagRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public NewsArticleTagDto findById(Integer id) {
        NewsArticleTag newsArticleTag = newsArticleTagRepository.findById(id).orElseGet(NewsArticleTag::new);
        ValidationUtil.isNull(newsArticleTag.getId(),"NewsArticleTag","id",id);
        return newsArticleTagMapper.toDto(newsArticleTag);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public NewsArticleTagDto create(NewsArticleTag resources) {
        return newsArticleTagMapper.toDto(newsArticleTagRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(NewsArticleTag resources) {
        NewsArticleTag newsArticleTag = newsArticleTagRepository.findById(resources.getId()).orElseGet(NewsArticleTag::new);
        ValidationUtil.isNull( newsArticleTag.getId(),"NewsArticleTag","id",resources.getId());
        newsArticleTag.copy(resources);
        newsArticleTagRepository.save(newsArticleTag);
    }

    @Override
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            newsArticleTagRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<NewsArticleTagDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (NewsArticleTagDto newsArticleTag : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("新闻ID", newsArticleTag.getArticleId());
            map.put("标签ID", newsArticleTag.getTagId());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}