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

import org.el.modules.news.domain.NewsTag;
import lombok.RequiredArgsConstructor;
import org.el.modules.news.repository.NewsTagRepository;
import org.el.modules.news.service.NewsTagService;
import org.el.modules.news.service.dto.NewsTagDto;
import org.el.modules.news.service.dto.NewsTagQueryCriteria;
import org.el.modules.news.service.mapstruct.NewsTagMapper;
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
public class NewsTagServiceImpl implements NewsTagService {

    private final NewsTagRepository newsTagRepository;
    private final NewsTagMapper newsTagMapper;

    @Override
    public Map<String,Object> queryAll(NewsTagQueryCriteria criteria, Pageable pageable){
        Page<NewsTag> page = newsTagRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(newsTagMapper::toDto));
    }

    @Override
    public List<NewsTagDto> queryAll(NewsTagQueryCriteria criteria){
        return newsTagMapper.toDto(newsTagRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public NewsTagDto findById(Integer id) {
        NewsTag newsTag = newsTagRepository.findById(id).orElseGet(NewsTag::new);
        ValidationUtil.isNull(newsTag.getId(),"NewsTag","id",id);
        return newsTagMapper.toDto(newsTag);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public NewsTagDto create(NewsTag resources) {
        return newsTagMapper.toDto(newsTagRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(NewsTag resources) {
        NewsTag newsTag = newsTagRepository.findById(resources.getId()).orElseGet(NewsTag::new);
        ValidationUtil.isNull( newsTag.getId(),"NewsTag","id",resources.getId());
        newsTag.copy(resources);
        newsTagRepository.save(newsTag);
    }

    @Override
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            newsTagRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<NewsTagDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (NewsTagDto newsTag : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("创建人", newsTag.getCreateBy());
            map.put("创建时间", newsTag.getCreateTime());
            map.put("更新人", newsTag.getUpdateBy());
            map.put("更新时间", newsTag.getUpdateTime());
            map.put("标签名称", newsTag.getTagName());
            map.put("标签编码", newsTag.getTagCode());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}