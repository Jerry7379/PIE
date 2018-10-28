package com.sjcl.zrsy.controller;

import com.sjcl.zrsy.domain.dto.RestfulResult;
import com.sjcl.zrsy.domain.dto.SearchId;
import com.sjcl.zrsy.service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    @Autowired
    ISearchService searchService;

    @GetMapping("/searchid")
    @ResponseBody
    public RestfulResult get(@RequestParam String id){
        SearchId obj = searchService.get(id);

        if (obj != null) {
            return RestfulResult.ok(obj);
        } else {
            return RestfulResult.errorMsg("输入错误!该猪不存在!");
        }
    }
}
