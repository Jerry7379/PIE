package com.sjcl.zrsy.service;

import com.sjcl.zrsy.domain.dto.FarmOperation;
import com.sjcl.zrsy.domain.dto.FarmReception;

/**
 * 养殖场服务接口
 */
public interface IFarmService {
     boolean farmReception(FarmReception farmReception);
     boolean farmOperation(FarmOperation farmOperation) ;
     boolean idCardExists(String id);


}
