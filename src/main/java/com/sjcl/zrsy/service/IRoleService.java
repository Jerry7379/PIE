package com.sjcl.zrsy.service;

import com.sjcl.zrsy.domain.dto.RoleLogin;
import com.sjcl.zrsy.domain.po.RoleRegistration;

import java.util.List;

public interface IRoleService {


    boolean registration(RoleRegistration roleRegistration);

    List<String> login(RoleLogin roleLogin) ;

    String picturechange(String id,String picturecode);
}
