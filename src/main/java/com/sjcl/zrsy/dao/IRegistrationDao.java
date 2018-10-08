package com.sjcl.zrsy.dao;

import com.sjcl.zrsy.domain.po.Registration;

public interface IRegistrationDao {
    Registration getLoginByRegistrationId(String registrationId);

    boolean insertRegistration(Registration registration);
}
