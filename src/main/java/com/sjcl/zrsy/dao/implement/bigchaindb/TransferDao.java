package com.sjcl.zrsy.dao.implement.bigchaindb;

import com.sjcl.zrsy.bigchaindb.BigchaindbUtil;
import com.sjcl.zrsy.bigchaindb.KeyPairService;
import com.sjcl.zrsy.dao.IRegistrationDao;
import com.sjcl.zrsy.dao.ITransferDao;
import com.sjcl.zrsy.domain.dto.TransferOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public class TransferDao implements ITransferDao {

    @Autowired
    IRegistrationDao registrationDao;

    @Override
    public boolean transfer(@RequestBody TransferOperation transferOperation) throws Exception {

        if(BigchaindbUtil.transferTo(BigchaindbUtil.getAssetId(transferOperation.getPigId()), KeyPairService.getPublicKey(transferOperation.getType(),transferOperation.getRoleid()))!=null){
            return true;
        }
        else{
            return false;
        }
    }
}
