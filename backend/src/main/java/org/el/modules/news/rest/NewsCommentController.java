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
import org.el.modules.news.domain.NewsComment;
import org.el.modules.news.service.NewsCommentService;
import org.el.modules.news.service.dto.NewsCommentQueryCriteria;
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
@Api(tags = "新闻评论管理")
@RequestMapping("/api/newsComment")
public class NewsCommentController {

    private final NewsCommentService newsCommentService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('newsComment:list')")
    public void exportNewsComment(HttpServletResponse response, NewsCommentQueryCriteria criteria) throws IOException {
        newsCommentService.download(newsCommentService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询新闻评论")
    @ApiOperation("查询新闻评论")
    @PreAuthorize("@el.check('newsComment:list')")
    public ResponseEntity<Object> queryNewsComment(NewsCommentQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(newsCommentService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增新闻评论")
    @ApiOperation("新增新闻评论")
    @PreAuthorize("@el.check('newsComment:add')")
    public ResponseEntity<Object> createNewsComment(@Validated @RequestBody NewsComment resources){
        return new ResponseEntity<>(newsCommentService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改新闻评论")
    @ApiOperation("修改新闻评论")
    @PreAuthorize("@el.check('newsComment:edit')")
    public ResponseEntity<Object> updateNewsComment(@Validated @RequestBody NewsComment resources){
        newsCommentService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除新闻评论")
    @ApiOperation("删除新闻评论")
    @PreAuthorize("@el.check('newsComment:del')")
    public ResponseEntity<Object> deleteNewsComment(@RequestBody Integer[] ids) {
        newsCommentService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}