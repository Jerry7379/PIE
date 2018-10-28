package com.sjcl.zrsy.dao;

import com.sjcl.zrsy.domain.po.TraceabilityIdcard;

import java.io.IOException;

/**
 * TraceabilityIdcard data access object
 */
public interface ITraceabilityIdcardDao {
    /**
     * check is exsits
     * @param id
     * @return
     */
    boolean exsits(String id);

    /**
     * insert traceabilityIdcard
     * @param initialFarm
     * @return
     */
    String insert(TraceabilityIdcard initialFarm);

    /**
     * update logistics
     * @param logistics
     * @return
     */
    boolean updateLogistics(TraceabilityIdcard logistics) ;

    /**
     * update market
     * @param market
     * @return
     */
    boolean updateMarket(TraceabilityIdcard market) ;

    /**
     * update quarantine
     * @param quarantine
     * @return
     */
    boolean updateQuarantine(TraceabilityIdcard quarantine) ;

    /**
     * update acid
     * @param acid
     * @return
     */
    boolean updateAcid(TraceabilityIdcard acid) ;
}
