package com.sjcl.zrsy.dao;

import com.sjcl.zrsy.domain.po.TraceabilityIdcard;

import java.io.IOException;

public interface ITraceabilityIdcardDao {
    /**
     * check is exsits
     * @param id
     * @return
     */
    boolean exsits(String id) throws IOException;

    /**
     * insert traceabilityIdcard
     * @param initialFarm
     * @return
     */
    boolean insert(TraceabilityIdcard initialFarm);

    /**
     * update logistics
     * @param logistics
     * @return
     */
    boolean updateLogistics(TraceabilityIdcard logistics) throws Exception;

    /**
     * update market
     * @param market
     * @return
     */
    boolean updateMarket(TraceabilityIdcard market) throws IOException;

    /**
     * update quarantine
     * @param quarantine
     * @return
     */
    boolean updateQuarantine(TraceabilityIdcard quarantine) throws IOException;

    /**
     * update acid
     * @param acid
     * @return
     */
    boolean updateAcid(TraceabilityIdcard acid) throws IOException;
}
