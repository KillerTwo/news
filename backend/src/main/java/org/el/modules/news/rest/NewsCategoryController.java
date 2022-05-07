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
package org.el.modules.news.rest;

import org.el.annotation.Log;
import org.el.modules.news.domain.NewsCategory;
import org.el.modules.news.service.NewsCategoryService;
import org.el.modules.news.service.dto.NewsCategoryQueryCriteria;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @website https://el-admin.vip
* @author qingtian@gmail.com
* @date 2022-05-08
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "新闻分类管理")
@RequestMapping("/api/newsCategory")
public class NewsCategoryController {

    private final NewsCategoryService newsCategoryService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('newsCategory:list')")
    public void exportNewsCategory(HttpServletResponse response, NewsCategoryQueryCriteria criteria) throws IOException {
        newsCategoryService.download(newsCategoryService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询新闻分类")
    @ApiOperation("查询新闻分类")
    @PreAuthorize("@el.check('newsCategory:list')")
    public ResponseEntity<Object> queryNewsCategory(NewsCategoryQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(newsCategoryService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增新闻分类")
    @ApiOperation("新增新闻分类")
    @PreAuthorize("@el.check('newsCategory:add')")
    public ResponseEntity<Object> createNewsCategory(@Validated @RequestBody NewsCategory resources){
        return new ResponseEntity<>(newsCategoryService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改新闻分类")
    @ApiOperation("修改新闻分类")
    @PreAuthorize("@el.check('newsCategory:edit')")
    public ResponseEntity<Object> updateNewsCategory(@Validated @RequestBody NewsCategory resources){
        newsCategoryService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除新闻分类")
    @ApiOperation("删除新闻分类")
    @PreAuthorize("@el.check('newsCategory:del')")
    public ResponseEntity<Object> deleteNewsCategory(@RequestBody Integer[] ids) {
        newsCategoryService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}