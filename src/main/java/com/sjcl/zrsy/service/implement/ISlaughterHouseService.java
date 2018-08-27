package com.sjcl.zrsy.service.implement;

import com.sjcl.zrsy.domain.PigSlaughterReceiver;
import com.sjcl.zrsy.domain.SlaughterAcid;

public interface ISlaughterHouseService {
    String slaughterreception(PigSlaughterReceiver receiver);//屠宰检查
    String slaughteroperation(SlaughterAcid slaughterAcid);
}
