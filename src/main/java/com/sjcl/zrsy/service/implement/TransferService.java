package com.sjcl.zrsy.service.implement;

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
    public boolean isNext(String registrationId) {
        Registration nextCandidate = roleService.login(registrationId);
        Registration user = KeyPairHolder.getUser();
        return (user.isNext(nextCandidate));
    }

    @Override
    public void transfer(String pigId, String registrationId) throws Exception {
        transferDao.transfer(pigId, registrationId);
    }
}
