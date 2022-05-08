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
import org.el.modules.news.domain.NewsArticleTag;
import org.el.modules.news.service.NewsArticleTagService;
import org.el.modules.news.service.dto.NewsArticleTagQueryCriteria;
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
@Api(tags = "新闻标签关联管理")
@RequestMapping("/api/newsArticleTag")
public class NewsArticleTagController {

    private final NewsArticleTagService newsArticleTagService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('newsArticleTag:list')")
    public void exportNewsArticleTag(HttpServletResponse response, NewsArticleTagQueryCriteria criteria) throws IOException {
        newsArticleTagService.download(newsArticleTagService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询新闻标签关联")
    @ApiOperation("查询新闻标签关联")
    @PreAuthorize("@el.check('newsArticleTag:list')")
    public ResponseEntity<Object> queryNewsArticleTag(NewsArticleTagQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(newsArticleTagService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增新闻标签关联")
    @ApiOperation("新增新闻标签关联")
    @PreAuthorize("@el.check('newsArticleTag:add')")
    public ResponseEntity<Object> createNewsArticleTag(@Validated @RequestBody NewsArticleTag resources){
        return new ResponseEntity<>(newsArticleTagService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改新闻标签关联")
    @ApiOperation("修改新闻标签关联")
    @PreAuthorize("@el.check('newsArticleTag:edit')")
    public ResponseEntity<Object> updateNewsArticleTag(@Validated @RequestBody NewsArticleTag resources){
        newsArticleTagService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除新闻标签关联")
    @ApiOperation("删除新闻标签关联")
    @PreAuthorize("@el.check('newsArticleTag:del')")
    public ResponseEntity<Object> deleteNewsArticleTag(@RequestBody Integer[] ids) {
        newsArticleTagService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}