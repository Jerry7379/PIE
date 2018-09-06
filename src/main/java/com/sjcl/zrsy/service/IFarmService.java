package com.sjcl.zrsy.service;

import com.sjcl.zrsy.domain.dto.FarmReception;
import com.sjcl.zrsy.domain.po.Operation;

/**
 * 养殖场服务接口
 */
public interface IFarmService {
     boolean farmReception(FarmReception farmReception);
     boolean farmOperation(Operation operation) ;
     boolean idCardExists(String id);


}
