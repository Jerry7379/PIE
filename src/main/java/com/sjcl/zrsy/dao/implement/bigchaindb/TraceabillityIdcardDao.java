package com.sjcl.zrsy.dao.implement.bigchaindb;

import com.bigchaindb.model.Assets;
import com.sjcl.zrsy.bigchaindb.BigchaindbUtil;
import com.sjcl.zrsy.bigchaindb.KeyPairHolder;
import com.sjcl.zrsy.dao.ITraceabilityIdcardDao;
import com.sjcl.zrsy.domain.po.TraceabilityIdcard;
import org.springframework.dao.DataAccessException;

import java.io.IOException;
import java.security.KeyPair;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.bigchaindb.api.AssetsApi.getAssets;

public class TraceabillityIdcardDao implements ITraceabilityIdcardDao {

    KeyPair keyPair= KeyPairHolder.getKeyPair();

    /**
     * 判断猪id是否存在
     * @param id
     * @return
     * @throws IOException
     */
    @Override
    public boolean exsits(String id) {
        Assets assets=new Assets();
        try {
            if(getAssets(id).size()!=0) {
                Map<String, Object> data = (Map<String, Object>) assets.getAssets().get(0).getData();
                if(data.get("type").equals("pig")){
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }
        } catch (IOException e) {
            return false;
        }


    }

    /**
     * 新猪出生，创建资产，
     * @param initialFarm
     * @return
     */
    @Override
    public boolean insert(TraceabilityIdcard initialFarm) {
        Map<String,Object>idCard=new HashMap<String,Object>();
        idCard.put("id",initialFarm.getId());
        idCard.put("birthday",initialFarm.getBirthday());
        idCard.put("breed",initialFarm.getBreed());
        idCard.put("gender",initialFarm.getGender());
        idCard.put("birthweight",initialFarm.getBirthweight());
        Map<String,Object> metadata=new HashMap<>();
        metadata.put("traceabilityIdCard",idCard);
        idCard.put("type","pig");
        try {
            BigchaindbUtil.createAsset(idCard, metadata, keyPair);//返回tansaction id/asset id
        }catch (Exception e)
        {
            return false;
        }
        return true;
    }

    /**
     * 更新猪身份证的物流相关信息
     * @param logistics
     * @return
     * @throws IOException
     */
    @Override
    public boolean updateLogistics(TraceabilityIdcard logistics) {
        if(exsits(logistics.getId())) {
            Map<String, Object> idCard = new HashMap<>();
            idCard.put("id", logistics.getId());
            idCard.put("logisticsId", logistics.getLogisticsId());
            idCard.put("carId", logistics.getCarId());
            idCard.put("driverId", logistics.getDriverId());
            Map<String, Object> metadata = new HashMap<>();
            metadata.put("traceabilityIdCard", idCard);
            try {
                BigchaindbUtil.transferIdCardAndOperation(metadata, keyPair);
            } catch (Exception e) {
                return false;
            }
            return true;
        }
        else {
            return false;
        }

    }

    /**
     * 更新猪身份证的超市相关信息
     * @param market
     * @return
     * @throws IOException
     */
    @Override
    public boolean updateMarket(TraceabilityIdcard market)  {
        if(exsits(market.getId())) {
            Map<String, Object> idCard = new HashMap<>();
            idCard.put("id", market.getId());
            idCard.put("supermarketId", market.getSupermarketId());
            Map<String, Object> metadata = new HashMap<>();
            metadata.put("traceabilityIdCard", idCard);
            try {
                BigchaindbUtil.transferIdCardAndOperation(metadata, keyPair);
            } catch (Exception e) {
                return false;
            }
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 更新猪身份证的屠宰场相关信息
     * @param quarantine
     * @return
     * @throws IOException
     */
    @Override
    public boolean updateQuarantine(TraceabilityIdcard quarantine)  {
        if(exsits(quarantine.getId())) {
            Map<String, Object> idCard = new HashMap<>();
            idCard.put("id", quarantine.getId());
            idCard.put("checkerId", quarantine.getCheckerId());
            idCard.put("slaughterhouseId", quarantine.getSlaughterhouseId());
            idCard.put("isCheck", quarantine.getIscheck());
            Map<String, Object> metadata = new HashMap<>();
            metadata.put("traceabilityIdCard", idCard);
            try {
                BigchaindbUtil.transferIdCardAndOperation(metadata, keyPair);
            } catch (Exception e) {
                return false;
            }
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 更新猪身份证的排酸相关信息
     * @param acid
     * @return
     * @throws IOException
     */
    @Override
    public boolean updateAcid(TraceabilityIdcard acid)  {
        if(exsits(acid.getId())) {
            Map<String, Object> idCard = new HashMap<>();
            idCard.put("id", acid.getId());
            idCard.put("aciderId", acid.getAciderId());
            idCard.put("isAcid", acid.getIsacid());
            Map<String, Object> metadata = new HashMap<>();
            metadata.put("traceabilityIdCard", idCard);
            try {
                BigchaindbUtil.transferIdCardAndOperation(metadata, keyPair);
            } catch (Exception e) {
                return false;
            }
            return true;
        }
        else {
            return false;
        }
    }
}
