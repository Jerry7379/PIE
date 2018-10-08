package com.sjcl.zrsy.service;

import com.sjcl.zrsy.domain.dto.SlaughterOperation;
import com.sjcl.zrsy.domain.dto.SlaughterReception;

public interface ISlaughterHouseService {
    boolean slaughterreception(SlaughterReception slaughterReception);//屠宰检查
    boolean slaughteroperation(SlaughterOperation slaughterOperation);
}
