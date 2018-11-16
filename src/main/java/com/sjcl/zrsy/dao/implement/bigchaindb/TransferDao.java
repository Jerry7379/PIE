package com.sjcl.zrsy.dao.implement.bigchaindb;

import com.sjcl.zrsy.bigchaindb.BigchaindbUtil;
import com.sjcl.zrsy.bigchaindb.KeyPairService;
import com.sjcl.zrsy.dao.IRegistrationDao;
import com.sjcl.zrsy.dao.ITransferDao;
import com.sjcl.zrsy.domain.dto.TransferOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TransferDao implements ITransferDao {

    @Autowired
    IRegistrationDao registrationDao;

    @Override
    public void transfer(TransferOperation transferOperation) throws Exception {
        BigchaindbUtil.transferTo(BigchaindbUtil.getAssetId(transferOperation.getPigId()), KeyPairService.getPublicKey(transferOperation.getType(),transferOperation.getRoleid())); //TODO test fail
    }
}
