package com.sjcl.zrsy.service.implement;

import com.sjcl.zrsy.dao.ISearchDao;
import com.sjcl.zrsy.domain.dto.SearchId;
import com.sjcl.zrsy.service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements ISearchService {


    @Autowired
    ISearchDao searchDao;

    @Override
    public SearchId get(String id){
        return searchDao.select(id);
    }
}
