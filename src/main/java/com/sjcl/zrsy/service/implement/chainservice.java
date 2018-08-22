package com.sjcl.zrsy.service.implement;


import com.sjcl.zrsy.dao.ChainDao;
import com.sjcl.zrsy.dao.PigDao;
import com.sjcl.zrsy.domain.PigSlaughterReceiver;
import com.sjcl.zrsy.domain.Registration;
import com.sjcl.zrsy.service.IPigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



public class chainservice /*implements IPigService*/{
    @Autowired
    ChainDao chainDao;

    //@Override
    public String registration(Registration registration)
    {
        return "";
    }

   // @Override
    public String slaughterreceiver(PigSlaughterReceiver receiver)
    {
        return  "hello";
    }

   // @Override
    public String test()  {
        //return chainDao.chaintest();
        return "";
    }

}
