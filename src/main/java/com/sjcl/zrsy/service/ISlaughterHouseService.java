package com.sjcl.zrsy.service;

import com.sjcl.zrsy.domain.dto.SlaughterReception;
import com.sjcl.zrsy.domain.dto.SlaughterOperation;

/**
 * slaughter house service
 */
public interface ISlaughterHouseService {
    /**
     * slaughter receive a pig
     * @param slaughterReception receive object
     * @return <tt>true</tt> if seccess
     */
    boolean slaughterreception(SlaughterReception slaughterReception);

    /**
     * slaughter operation a pig
     * @param slaughterOperation
     * @return <tt>true</tt> if seccess
     */
    boolean slaughteroperation(SlaughterOperation slaughterOperation);
}
