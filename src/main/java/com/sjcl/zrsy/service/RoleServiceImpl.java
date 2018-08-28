package com.sjcl.zrsy.service;

import com.sjcl.zrsy.dao.RoleDao;
import com.sjcl.zrsy.domain.*;
import com.sjcl.zrsy.service.implement.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleDao roledao;


//    @Override
//    public String insertBirth(MarketReception marketRec){
//        return pigDao.insertMR(marketRec);
//    }

//    @Override
//    public String insertMarketWork(MarketOperation marketWork){
//        return pigDao.insertMW(marketWork);
//}

    @Override
    public boolean registration(RoleRegistration registration)
    {
        return roledao.insertRegistration(registration);
    }

//    public String insertSlwork(SlaughterOperation slaughterAcid){
//        return pigDao.insertMAC(slaughterAcid);
//    }
//
//
//    @Override
//    public String insertLR(LogisticsReceive logisticsReceive)
//    {
//        return pigDao.insertLR(logisticsReceive);
//    }
//
//    @Override
//    public String insertLW(LogisticsOperation logisticsWork)
//    {
//        return pigDao.insertLW(logisticsWork);
//    }
//
//    @Override
//    public String slaughterreceiver(SlaughterReception receiver)
//    {
//        return pigDao.slaughterreceive(receiver);
//    }

    @Override
    public List<String> login(RoleLogin user)
    {
        return roledao.getLogin(user);
    }

    @Override
    public String  picturechange(String id,String picture){
        return roledao.picturechange(id,picture);
    }
}
