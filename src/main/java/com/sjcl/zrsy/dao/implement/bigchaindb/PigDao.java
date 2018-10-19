package com.sjcl.zrsy.dao.implement.bigchaindb;

import com.bigchaindb.api.OutputsApi;
import com.bigchaindb.model.Outputs;
import com.sjcl.zrsy.bigchaindb.KeyPairHolder;
import com.sjcl.zrsy.dao.IPigDao;

import java.io.IOException;

public class PigDao implements IPigDao {

    @Override
    public int getCountCurrentRegistration() {
        try {
            Outputs outputs = OutputsApi.getUnspentOutputs(KeyPairHolder.base58PublicKey());
            return outputs.getOutput().size() - 1;
        } catch (IOException e) {

            return 0;
        }
    }
}
