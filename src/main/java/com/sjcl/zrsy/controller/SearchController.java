package com.sjcl.zrsy.controller;

import com.sjcl.zrsy.domain.dto.RestfulResult;
import com.sjcl.zrsy.domain.dto.SearchId;
import com.sjcl.zrsy.service.ISearchService;
import com.sjcl.zrsy.tendermint.ActionClass;
import com.sjcl.zrsy.tendermint.ActionMethod;
import org.springframework.beans.factory.annotation.Autowired;

@ActionClass
public class SearchController {

    @Autowired
    ISearchService searchService;

    @ActionMethod("searchid")
    public RestfulResult get(String id){
        SearchId obj = searchService.get(id);

        if (obj != null) {
            return RestfulResult.ok(obj);
        } else {
            return RestfulResult.errorMsg("输入错误!该猪不存在!");
        }
    }
}
