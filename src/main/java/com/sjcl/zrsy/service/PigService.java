package com.sjcl.zrsy.service;

import com.sjcl.zrsy.dao.PigDao;
import com.sjcl.zrsy.domain.Zuoye;

public class PigService {
     private PigDao pigDao = new PigDao();

    public void insert(Zuoye pigBir){

        pigDao.insertBirth(pigBir);
    }
}
