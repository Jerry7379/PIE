package com.sjcl.zrsy.service.implement;

import com.sjcl.zrsy.domain.FarmOperation;
import com.sjcl.zrsy.domain.FarmReception;

/**
 * 养殖场服务接口
 */
public interface IFarmService {
     boolean farmReception(FarmReception farmReception);
     boolean farmOperation(FarmOperation farmOperation) ;
     boolean farmReceptionCheck(String id);


}
