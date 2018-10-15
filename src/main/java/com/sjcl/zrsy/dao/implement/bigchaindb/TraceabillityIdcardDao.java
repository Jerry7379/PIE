package com.sjcl.zrsy.dao.implement.bigchaindb;

import com.sjcl.zrsy.bigchaindb.BigchaindbUtil;
import com.sjcl.zrsy.dao.ITraceabilityIdcardDao;
import com.sjcl.zrsy.domain.dto.BigchaindbData;
import com.sjcl.zrsy.domain.po.TraceabilityIdcard;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public class TraceabillityIdcardDao implements ITraceabilityIdcardDao {
    /**
     * 判断猪id是否存在
     *
     * @param id
     * @return
     * @throws IOException
     */
    @Override
    public boolean exsits(String id) {
        return BigchaindbUtil.assetIsExist(id, String.class);
    }

    /**
     * 新猪出生，创建资产，
     *
     * @param initialFarm
     * @return
     */
    @Override
    public String insert(TraceabilityIdcard initialFarm) {
        try {
            return BigchaindbUtil.createAsset("pig", initialFarm);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 更新猪身份证的物流相关信息
     *
     * @param logistics
     * @return
     * @throws IOException
     */
    @Override
    public boolean updateLogistics(TraceabilityIdcard logistics) {
        return updateTraceabilityIdcard(logistics);

    }

    /**
     * 更新猪身份证的超市相关信息
     *
     * @param market
     * @return
     * @throws IOException
     */
    @Override
    public boolean updateMarket(TraceabilityIdcard market) {
        return updateTraceabilityIdcard(market);
    }

    /**
     * 更新猪身份证的屠宰场相关信息
     *
     * @param quarantine
     * @return
     * @throws IOException
     */
    @Override
    public boolean updateQuarantine(TraceabilityIdcard quarantine) {
        return updateTraceabilityIdcard(quarantine);
    }

    /**
     * 更新猪身份证的排酸相关信息
     *
     * @param acid
     * @return
     * @throws IOException
     */
    @Override
    public boolean updateAcid(TraceabilityIdcard acid) {
        return updateTraceabilityIdcard(acid);
    }

    private boolean updateTraceabilityIdcard(TraceabilityIdcard traceabilityIdcard) {
        if (exsits(traceabilityIdcard.getId())) {
            try {
                BigchaindbData metaData = new BigchaindbData(traceabilityIdcard);
                BigchaindbUtil.transferToSelf(metaData, traceabilityIdcard.getId());
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }
}
