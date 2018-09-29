package com.sjcl.zrsy.service;

import com.sjcl.zrsy.domain.dto.FarmReception;
import com.sjcl.zrsy.domain.po.Operation;

import java.io.IOException;

/**
 * 养殖场服务接口
 */
public interface IFarmService {
     boolean farmReception(FarmReception farmReception);
     boolean farmOperation(Operation Operation) ;
     boolean idCardExists(String id) ;


}
