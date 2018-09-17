package com.sjcl.zrsy.action;

import com.sjcl.zrsy.domain.dto.RestfulResult;
import com.sjcl.zrsy.domain.dto.SearchId;
import com.sjcl.zrsy.service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SearchAction {

    @Autowired
    ISearchService searchService;

    public RestfulResult get(String id){
        SearchId obj = searchService.get(id);

        if (obj != null) {
            return RestfulResult.ok(obj);
        } else {
            return RestfulResult.errorMsg("输入错误!该猪不存在!");
        }
    }
}
