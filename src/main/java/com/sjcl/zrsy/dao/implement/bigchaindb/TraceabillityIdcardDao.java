package com.sjcl.zrsy.dao.implement.bigchaindb;

import com.bigchaindb.api.TransactionsApi;
import com.bigchaindb.constants.Operations;
import com.sjcl.zrsy.bigchaindb.BigchaindbUtil;
import com.sjcl.zrsy.dao.ITraceabilityIdcardDao;
import com.sjcl.zrsy.domain.dto.AssetData;
import com.sjcl.zrsy.domain.dto.MetaData;

import com.sjcl.zrsy.domain.po.TraceabilityIdcard;
import java.io.IOException;
import java.util.Map;

import com.bigchaindb.api.AssetsApi;

public class TraceabillityIdcardDao implements ITraceabilityIdcardDao {
    /**
     * 判断猪id是否存在
     * @param id
     * @return
     * @throws IOException
     */
    @Override
    public boolean exsits(String id) {
        try {
//            TransactionsApi.getTransactionsByAssetId(id, Operations.CREATE);

            if(AssetsApi.getAssets(id).size()!=0) {
                Map<String, Object> data = (Map<String, Object>) AssetsApi.getAssets(id).getAssets().get(0).getData();
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
        MetaData metaData=new MetaData(OperationDao.OPERATION_TRACEABILLITYIDCARD, OperationDao.FARM_ROLE,initialFarm);
        AssetData assetData =new AssetData("pig");

        try {
            BigchaindbUtil.createAsset(assetData, metaData);//返回tansaction id/asset id
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
        return updateTraceabilityIdcard(logistics,OperationDao.LOGISTICS_ROLE);

    }

    /**
     * 更新猪身份证的超市相关信息
     * @param market
     * @return
     * @throws IOException
     */
    @Override
    public boolean updateMarket(TraceabilityIdcard market)  {
        return updateTraceabilityIdcard(market,OperationDao.MARKET_ROLE);
    }

    /**
     * 更新猪身份证的屠宰场相关信息
     * @param quarantine
     * @return
     * @throws IOException
     */
    @Override
    public boolean updateQuarantine(TraceabilityIdcard quarantine)  {
        return updateTraceabilityIdcard(quarantine,OperationDao.SLAUGHTER_ROLE);
    }

    /**
     * 更新猪身份证的排酸相关信息
     * @param acid
     * @return
     * @throws IOException
     */
    @Override
    public boolean updateAcid(TraceabilityIdcard acid)  {
        return updateTraceabilityIdcard(acid,OperationDao.SLAUGHTER_ROLE);
    }

    private boolean updateTraceabilityIdcard(TraceabilityIdcard traceabilityIdcard,String role){
        if(exsits(traceabilityIdcard.getId())) {
            MetaData metaData=new MetaData(OperationDao.OPERATION_TRACEABILLITYIDCARD,role,traceabilityIdcard);
            try {
                BigchaindbUtil.transferToSelf(metaData,traceabilityIdcard.getId());
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
