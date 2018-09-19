package com.sjcl.zrsy.dao;

import com.sjcl.zrsy.domain.dto.SearchId;

public interface ISearchDao {
    SearchId select(String id);
}
