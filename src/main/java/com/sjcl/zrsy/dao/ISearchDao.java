package com.sjcl.zrsy.dao;

import com.sjcl.zrsy.domain.dto.SearchId;

/**
 * search data access dao
 */
public interface ISearchDao {
    /**
     * search by pigId
     * @param id pigId
     * @return search
     */
    SearchId select(String id);
}
