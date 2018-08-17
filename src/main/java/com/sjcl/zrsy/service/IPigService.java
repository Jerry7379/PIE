package com.sjcl.zrsy.service;

import com.sjcl.zrsy.domain.Pig_Birth;
import com.sjcl.zrsy.domain.Registration;
import com.sjcl.zrsy.domain.User;

public interface IPigService {
    void insertpig(Pig_Birth newpig);

    void registration(Registration registration);


}
