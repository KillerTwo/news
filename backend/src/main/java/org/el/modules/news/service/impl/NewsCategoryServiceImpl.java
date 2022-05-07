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

import org.el.modules.news.domain.NewsCategory;
import lombok.RequiredArgsConstructor;
import org.el.modules.news.repository.NewsCategoryRepository;
import org.el.modules.news.service.NewsCategoryService;
import org.el.modules.news.service.dto.NewsCategoryDto;
import org.el.modules.news.service.dto.NewsCategoryQueryCriteria;
import org.el.modules.news.service.mapstruct.NewsCategoryMapper;
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
public class NewsCategoryServiceImpl implements NewsCategoryService {

    private final NewsCategoryRepository newsCategoryRepository;
    private final NewsCategoryMapper newsCategoryMapper;

    @Override
    public Map<String,Object> queryAll(NewsCategoryQueryCriteria criteria, Pageable pageable){
        Page<NewsCategory> page = newsCategoryRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(newsCategoryMapper::toDto));
    }

    @Override
    public List<NewsCategoryDto> queryAll(NewsCategoryQueryCriteria criteria){
        return newsCategoryMapper.toDto(newsCategoryRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public NewsCategoryDto findById(Integer id) {
        NewsCategory newsCategory = newsCategoryRepository.findById(id).orElseGet(NewsCategory::new);
        ValidationUtil.isNull(newsCategory.getId(),"NewsCategory","id",id);
        return newsCategoryMapper.toDto(newsCategory);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public NewsCategoryDto create(NewsCategory resources) {
        return newsCategoryMapper.toDto(newsCategoryRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(NewsCategory resources) {
        NewsCategory newsCategory = newsCategoryRepository.findById(resources.getId()).orElseGet(NewsCategory::new);
        ValidationUtil.isNull( newsCategory.getId(),"NewsCategory","id",resources.getId());
        newsCategory.copy(resources);
        newsCategoryRepository.save(newsCategory);
    }

    @Override
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            newsCategoryRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<NewsCategoryDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (NewsCategoryDto newsCategory : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("创建人", newsCategory.getCreateBy());
            map.put("创建时间", newsCategory.getCreateTime());
            map.put("更新人", newsCategory.getUpdateBy());
            map.put("更新时间", newsCategory.getUpdateTime());
            map.put("分类名称", newsCategory.getCategoryName());
            map.put("分类排序", newsCategory.getSort());
            map.put("分类代码", newsCategory.getCategoryCode());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}