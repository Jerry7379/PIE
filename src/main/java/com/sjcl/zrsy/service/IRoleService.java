package com.sjcl.zrsy.service;

import com.sjcl.zrsy.domain.po.Registration;

/**
 * role service
 */
public interface IRoleService {
    /**
     * registration.
     *
     * @param registration
     * @return <tt>true</tt> if success
     */
    boolean registration(Registration registration);

    /**
     * login
     * @param registrationId
     * @return
     */
    Registration login(String  registrationId) ;
}
