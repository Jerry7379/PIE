package com.sjcl.zrsy.service;


import com.sjcl.zrsy.domain.PigSlaughterReceiver;
import com.sjcl.zrsy.domain.Registration;
import com.sjcl.zrsy.domain.User;


public interface IPigService {

    String registration(Registration registration);//角色注册服务

    String slaughterreceiver(PigSlaughterReceiver receiver);//屠宰检查

    String test(User user) ;



}
