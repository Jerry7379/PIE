package com.sjcl.zrsy.service.implement;

import com.sjcl.zrsy.domain.*;

import java.util.List;

public interface IRoleService {


    boolean registration(RoleRegistration roleRegistration);

    List<String> login(RoleLogin roleLogin) ;

    String picturechange(String id,String picturecode);
}
