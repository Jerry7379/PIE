package com.sjcl.zrsy.dao.implement.bigchaindb;

import com.sjcl.zrsy.bigchaindb.BigchaindbUtil;
import com.sjcl.zrsy.dao.IRegistrationDao;
import com.sjcl.zrsy.dao.ITransferDao;
import com.sjcl.zrsy.domain.po.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TransferDao implements ITransferDao {

    @Autowired
    IRegistrationDao registrationDao;

    @Override
    public void transfer(String pigId, String registrationId) throws Exception {
        Registration r  = registrationDao.getLoginByRegistrationId(registrationId);
        BigchaindbUtil.transferTo(pigId, r.getPublicKey());
    }
}
