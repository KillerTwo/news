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

import org.el.modules.news.domain.NewsLink;
import lombok.RequiredArgsConstructor;
import org.el.modules.news.repository.NewsLinkRepository;
import org.el.modules.news.service.NewsLinkService;
import org.el.modules.news.service.dto.NewsLinkDto;
import org.el.modules.news.service.dto.NewsLinkQueryCriteria;
import org.el.modules.news.service.mapstruct.NewsLinkMapper;
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
public class NewsLinkServiceImpl implements NewsLinkService {

    private final NewsLinkRepository newsLinkRepository;
    private final NewsLinkMapper newsLinkMapper;

    @Override
    public Map<String,Object> queryAll(NewsLinkQueryCriteria criteria, Pageable pageable){
        Page<NewsLink> page = newsLinkRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(newsLinkMapper::toDto));
    }

    @Override
    public List<NewsLinkDto> queryAll(NewsLinkQueryCriteria criteria){
        return newsLinkMapper.toDto(newsLinkRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public NewsLinkDto findById(Integer id) {
        NewsLink newsLink = newsLinkRepository.findById(id).orElseGet(NewsLink::new);
        ValidationUtil.isNull(newsLink.getId(),"NewsLink","id",id);
        return newsLinkMapper.toDto(newsLink);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public NewsLinkDto create(NewsLink resources) {
        return newsLinkMapper.toDto(newsLinkRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(NewsLink resources) {
        NewsLink newsLink = newsLinkRepository.findById(resources.getId()).orElseGet(NewsLink::new);
        ValidationUtil.isNull( newsLink.getId(),"NewsLink","id",resources.getId());
        newsLink.copy(resources);
        newsLinkRepository.save(newsLink);
    }

    @Override
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            newsLinkRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<NewsLinkDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (NewsLinkDto newsLink : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("创建人", newsLink.getCreateBy());
            map.put("创建时间", newsLink.getCreateTime());
            map.put("更新人", newsLink.getUpdateBy());
            map.put("更新时间", newsLink.getUpdateTime());
            map.put("链接名称", newsLink.getLinkName());
            map.put("链接地址", newsLink.getLinkUrl());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}