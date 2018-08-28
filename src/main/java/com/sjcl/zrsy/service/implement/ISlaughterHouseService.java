package com.sjcl.zrsy.service.implement;

import com.sjcl.zrsy.domain.SlaughterReception;
import com.sjcl.zrsy.domain.SlaughterOperation;

public interface ISlaughterHouseService {
    boolean slaughterreception(SlaughterReception receiver);//屠宰检查
    boolean slaughteroperation(SlaughterOperation slaughterAcid);
}
