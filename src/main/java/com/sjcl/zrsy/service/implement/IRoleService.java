package com.sjcl.zrsy.service.implement;

import com.sjcl.zrsy.domain.*;

import java.util.List;

public interface IRoleService {


    boolean registration(RoleRegistration registration);

    List<String> login(LoginUser user) ;

    String picturechange(String id,String picturecode);
}
