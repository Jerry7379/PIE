package com.sjcl.zrsy.service.implement;

import com.sjcl.zrsy.domain.SlaughterReception;
import com.sjcl.zrsy.domain.SlaughterOperation;

public interface ISlaughterHouseService {
    boolean slaughterreception(SlaughterReception slaughterReception);//屠宰检查
    boolean slaughteroperation(SlaughterOperation slaughterOperation);
}
