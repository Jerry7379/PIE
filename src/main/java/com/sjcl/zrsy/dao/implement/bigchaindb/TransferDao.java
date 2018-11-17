package com.sjcl.zrsy.dao.implement.bigchaindb;

import com.sjcl.zrsy.bigchaindb.BigchaindbUtil;
import com.sjcl.zrsy.bigchaindb.KeyPairService;
import com.sjcl.zrsy.dao.IRegistrationDao;
import com.sjcl.zrsy.dao.ITransferDao;
import com.sjcl.zrsy.domain.dto.BigchaindbData;
import com.sjcl.zrsy.domain.dto.TransferOperation;
import com.sjcl.zrsy.domain.po.TraceabilityIdcard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public class TransferDao implements ITransferDao {

    @Autowired
    IRegistrationDao registrationDao;

    @Override
    public boolean transfer(@RequestBody TransferOperation transferOperation) throws Exception {
        TraceabilityIdcard idcard=BigchaindbUtil.getWholeMetaData(BigchaindbUtil.getAssetId(transferOperation.getPigId()),TraceabilityIdcard.class);
        idcard.setSlaughterhouseId(transferOperation.getRoleid());
        String ret=BigchaindbUtil.transferTo(BigchaindbUtil.getAssetId(transferOperation.getPigId()), KeyPairService.getPublicKey(transferOperation.getType(),transferOperation.getRoleid()), new BigchaindbData<>(idcard));
        if(ret!=null){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean farmcheckout(String pigid){

        return false;
    }
}
