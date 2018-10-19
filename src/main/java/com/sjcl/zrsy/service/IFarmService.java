package com.sjcl.zrsy.service;

import com.sjcl.zrsy.domain.dto.FarmReception;
import com.sjcl.zrsy.domain.po.Operation;

import java.io.IOException;

/**
 * farm service
 */
public interface IFarmService {
     /**
      * farm receive a pig
      * @param farmReception
      * @return
      */
     String farmReception(FarmReception farmReception);

     /**
      * farm operation a pig
      * @param Operation
      * @return
      */
     boolean farmOperation(Operation Operation) ;

     /**
      * judge whether pig exist
      * @param id pigId
      * @return <tt>true</tt> if exist
      */
     boolean idCardExists(String id) ;


}
