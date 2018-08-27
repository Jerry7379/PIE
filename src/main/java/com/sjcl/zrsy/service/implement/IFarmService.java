package com.sjcl.zrsy.service.implement;

import com.sjcl.zrsy.domain.FarmOperation;
import com.sjcl.zrsy.domain.FarmReception;

/**
 * 养殖场服务接口
 */
public interface IFarmService {
    public boolean farmReception(FarmReception newpig);
    public boolean farmOperation(FarmOperation farmOperation) ;

}
