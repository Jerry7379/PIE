package com.sjcl.zrsy.service.implement;

import com.bigchaindb.api.AssetsApi;
import com.bigchaindb.api.TransactionsApi;
import com.sjcl.zrsy.bigchaindb.KeyPairHolder;
import com.sjcl.zrsy.dao.ITransferDao;
import com.sjcl.zrsy.domain.po.Registration;
import com.sjcl.zrsy.service.IRoleService;
import com.sjcl.zrsy.service.ITransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferService implements ITransferService {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private ITransferDao transferDao;

    @Override
    public void transfer(String pigId, String publicKeyHex) throws Exception {
        transferDao.transfer(pigId, publicKeyHex);
    }
}
