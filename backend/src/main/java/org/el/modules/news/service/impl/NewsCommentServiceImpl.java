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

import org.el.modules.news.domain.NewsComment;
import lombok.RequiredArgsConstructor;
import org.el.modules.news.repository.NewsCommentRepository;
import org.el.modules.news.service.NewsCommentService;
import org.el.modules.news.service.dto.NewsCommentDto;
import org.el.modules.news.service.dto.NewsCommentQueryCriteria;
import org.el.modules.news.service.mapstruct.NewsCommentMapper;
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
public class NewsCommentServiceImpl implements NewsCommentService {

    private final NewsCommentRepository newsCommentRepository;
    private final NewsCommentMapper newsCommentMapper;

    @Override
    public Map<String,Object> queryAll(NewsCommentQueryCriteria criteria, Pageable pageable){
        Page<NewsComment> page = newsCommentRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(newsCommentMapper::toDto));
    }

    @Override
    public List<NewsCommentDto> queryAll(NewsCommentQueryCriteria criteria){
        return newsCommentMapper.toDto(newsCommentRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public NewsCommentDto findById(Integer id) {
        NewsComment newsComment = newsCommentRepository.findById(id).orElseGet(NewsComment::new);
        ValidationUtil.isNull(newsComment.getId(),"NewsComment","id",id);
        return newsCommentMapper.toDto(newsComment);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public NewsCommentDto create(NewsComment resources) {
        return newsCommentMapper.toDto(newsCommentRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(NewsComment resources) {
        NewsComment newsComment = newsCommentRepository.findById(resources.getId()).orElseGet(NewsComment::new);
        ValidationUtil.isNull( newsComment.getId(),"NewsComment","id",resources.getId());
        newsComment.copy(resources);
        newsCommentRepository.save(newsComment);
    }

    @Override
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            newsCommentRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<NewsCommentDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (NewsCommentDto newsComment : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("创建人", newsComment.getCreateBy());
            map.put("创建时间", newsComment.getCreateTime());
            map.put("更新人", newsComment.getUpdateBy());
            map.put("更新时间", newsComment.getUpdateTime());
            map.put("新闻ID", newsComment.getNewsId());
            map.put("评论内容", newsComment.getContent());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}