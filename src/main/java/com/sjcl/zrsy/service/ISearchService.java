package com.sjcl.zrsy.service;

import com.sjcl.zrsy.domain.dto.SearchId;


/**
 * search service
 */
public interface ISearchService  {

    /**
     * obtain search result by pigId
     * @param id pigId
     * @return search result
     */
    SearchId get(String id);
}
