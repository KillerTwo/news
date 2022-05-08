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
import org.el.modules.news.domain.NewsArticle;
import org.el.modules.news.service.NewsArticleService;
import org.el.modules.news.service.dto.NewsArticleDto;
import org.el.modules.news.service.dto.NewsArticleQueryCriteria;
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
@Api(tags = "新闻管理")
@RequestMapping("/api/newsArticle")
public class NewsArticleController {

    private final NewsArticleService newsArticleService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('newsArticle:list')")
    public void exportNewsArticle(HttpServletResponse response, NewsArticleQueryCriteria criteria) throws IOException {
        newsArticleService.download(newsArticleService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询新闻")
    @ApiOperation("查询新闻")
    @PreAuthorize("@el.check('newsArticle:list')")
    public ResponseEntity<Object> queryNewsArticle(NewsArticleQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(newsArticleService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增新闻")
    @ApiOperation("新增新闻")
    @PreAuthorize("@el.check('newsArticle:add')")
    public ResponseEntity<Object> createNewsArticle(@Validated @RequestBody NewsArticle resources){
        return new ResponseEntity<>(newsArticleService.create(resources),HttpStatus.CREATED);
    }

    @PostMapping("/publish")
    @Log("发布新闻")
    @ApiOperation("发布新闻")
    public ResponseEntity<Object> createNewsArticle(@Validated @RequestBody NewsArticleDto resources){
        return new ResponseEntity<>(newsArticleService.publish(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改新闻")
    @ApiOperation("修改新闻")
    @PreAuthorize("@el.check('newsArticle:edit')")
    public ResponseEntity<Object> updateNewsArticle(@Validated @RequestBody NewsArticle resources){
        newsArticleService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除新闻")
    @ApiOperation("删除新闻")
    @PreAuthorize("@el.check('newsArticle:del')")
    public ResponseEntity<Object> deleteNewsArticle(@RequestBody Integer[] ids) {
        newsArticleService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}