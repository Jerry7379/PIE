package com.sjcl.zrsy.service;

import com.sjcl.zrsy.domain.dto.RoleLogin;
import com.sjcl.zrsy.domain.po.Registration;

public interface IRoleService {


    boolean registration(Registration registration);

    Registration login(RoleLogin roleLogin) ;

    String picturechange(String id,String picturecode);
}
