package com.sjcl.zrsy.dao.implement.bigchaindb;

import com.sjcl.zrsy.bigchaindb.BigchaindbUtil;
import com.sjcl.zrsy.dao.ITraceabilityIdcardDao;
import com.sjcl.zrsy.domain.dto.BigchaindbData;
import com.sjcl.zrsy.domain.po.TraceabilityIdcard;
import org.springframework.stereotype.Repository;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class TraceabillityIdcardDao implements ITraceabilityIdcardDao {
    public static final String ASSET_OBJECT = "pig";
    /**
     * 判断猪id是否存在
     *
     * @param id
     * @return
     * @throws IOException
     */
    @Override
    public boolean exsits(String id) {
        return BigchaindbUtil.assetIsExist(BigchaindbUtil.getAssetId(id), HashMap.class);
    }

    /**
     * 新猪出生，创建资产
     *
     * @param initialFarm
     * @return
     */
    @Override
    public String insert(TraceabilityIdcard initialFarm) {
        Map asset=new HashMap();
        asset.put("type",ASSET_OBJECT);
        asset.put("pigid",initialFarm.getId());
        try {
            return BigchaindbUtil.createAsset(asset, initialFarm);
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

    private boolean  updateTraceabilityIdcard(TraceabilityIdcard traceabilityIdcard) {
        try {
            TraceabilityIdcard idcard = BigchaindbUtil.getWholeMetaData(BigchaindbUtil.getAssetId(traceabilityIdcard.getId()), traceabilityIdcard.getClass());

            BeanInfo beanInfo = Introspector.getBeanInfo(idcard.getClass());
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();

            for (PropertyDescriptor pd : pds) {
                if (pd.getWriteMethod() == null || pd.getWriteMethod() == null) {
                    continue;
                }
                Object retVal = pd.getReadMethod().invoke(traceabilityIdcard);

                if (retVal != null) {
                    if(retVal.getClass().equals(int.class)&&((int)retVal==0)){
                        ;
                    }else {
                        pd.getWriteMethod().invoke(idcard, retVal);
                    }
                }
            }
            if (exsits(traceabilityIdcard.getId())) {

                BigchaindbData metaData = new BigchaindbData(idcard);
                BigchaindbUtil.transferToSelf(metaData, BigchaindbUtil.getAssetId(traceabilityIdcard.getId()));
                return true;

            } else {
                return false;
            }
        }catch (Exception e){
            return false;
        }

    }
}
