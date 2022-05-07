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
import org.el.modules.news.domain.NewsLink;
import org.el.modules.news.service.NewsLinkService;
import org.el.modules.news.service.dto.NewsLinkQueryCriteria;
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
@Api(tags = "友情链接管理")
@RequestMapping("/api/newsLink")
public class NewsLinkController {

    private final NewsLinkService newsLinkService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('newsLink:list')")
    public void exportNewsLink(HttpServletResponse response, NewsLinkQueryCriteria criteria) throws IOException {
        newsLinkService.download(newsLinkService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询友情链接")
    @ApiOperation("查询友情链接")
    @PreAuthorize("@el.check('newsLink:list')")
    public ResponseEntity<Object> queryNewsLink(NewsLinkQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(newsLinkService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增友情链接")
    @ApiOperation("新增友情链接")
    @PreAuthorize("@el.check('newsLink:add')")
    public ResponseEntity<Object> createNewsLink(@Validated @RequestBody NewsLink resources){
        return new ResponseEntity<>(newsLinkService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改友情链接")
    @ApiOperation("修改友情链接")
    @PreAuthorize("@el.check('newsLink:edit')")
    public ResponseEntity<Object> updateNewsLink(@Validated @RequestBody NewsLink resources){
        newsLinkService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除友情链接")
    @ApiOperation("删除友情链接")
    @PreAuthorize("@el.check('newsLink:del')")
    public ResponseEntity<Object> deleteNewsLink(@RequestBody Integer[] ids) {
        newsLinkService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}