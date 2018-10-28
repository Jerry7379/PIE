package com.sjcl.zrsy.dao;

import com.sjcl.zrsy.domain.po.Registration;

/**
 * Registration data access object
 */
public interface IRegistrationDao {
    /**
     * obtain registration by registration id
     * @param registrationId registration id
     * @return registration
     */
    Registration getRegistrationByRegistrationId(String registrationId);

    /**
     * insert registration to database
     * @param registration registration
     * @return <tt>true</tt> if inserted successfully
     */
    boolean insertRegistration(Registration registration);
}
