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
import org.el.modules.news.domain.NewsTag;
import org.el.modules.news.service.NewsTagService;
import org.el.modules.news.service.dto.NewsTagQueryCriteria;
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
@Api(tags = "新闻标签管理")
@RequestMapping("/api/newsTag")
public class NewsTagController {

    private final NewsTagService newsTagService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('newsTag:list')")
    public void exportNewsTag(HttpServletResponse response, NewsTagQueryCriteria criteria) throws IOException {
        newsTagService.download(newsTagService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询新闻标签")
    @ApiOperation("查询新闻标签")
    @PreAuthorize("@el.check('newsTag:list')")
    public ResponseEntity<Object> queryNewsTag(NewsTagQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(newsTagService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @GetMapping("/listAll")
    @Log("查询新闻标签")
    @ApiOperation("查询新闻标签")
    public ResponseEntity<Object> queryNewsTagAll(NewsTagQueryCriteria criteria){
        return new ResponseEntity<>(newsTagService.queryAll(criteria),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增新闻标签")
    @ApiOperation("新增新闻标签")
    @PreAuthorize("@el.check('newsTag:add')")
    public ResponseEntity<Object> createNewsTag(@Validated @RequestBody NewsTag resources){
        return new ResponseEntity<>(newsTagService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改新闻标签")
    @ApiOperation("修改新闻标签")
    @PreAuthorize("@el.check('newsTag:edit')")
    public ResponseEntity<Object> updateNewsTag(@Validated @RequestBody NewsTag resources){
        newsTagService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除新闻标签")
    @ApiOperation("删除新闻标签")
    @PreAuthorize("@el.check('newsTag:del')")
    public ResponseEntity<Object> deleteNewsTag(@RequestBody Integer[] ids) {
        newsTagService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}