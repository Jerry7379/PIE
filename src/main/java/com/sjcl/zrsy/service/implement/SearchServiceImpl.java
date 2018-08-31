package com.sjcl.zrsy.service.implement;

import com.sjcl.zrsy.dao.SearchDao;
import com.sjcl.zrsy.domain.SearchId;
import com.sjcl.zrsy.service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements ISearchService {


    @Autowired
    SearchDao searchDao;

    @Override
    public SearchId get(String id){
        return searchDao.select(id);
    }
}
