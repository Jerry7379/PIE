package com.sjcl.zrsy.dao.implement.bigchaindb;

import com.bigchaindb.api.OutputsApi;
import com.bigchaindb.model.Outputs;
import com.sjcl.zrsy.bigchaindb.KeyPairHolder;
import com.sjcl.zrsy.dao.IPigDao;

import java.io.IOException;

public class PigDao implements IPigDao {

    @Override
    public int getUnspentCountCurrentRegistration() {
        try {
            Outputs outputs = OutputsApi.getUnspentOutputs(KeyPairHolder.base58PublicKey());
            return outputs.getOutput().size() - 1;
        } catch (IOException e) {
            return 0;
        }
    }

    @Override
    public int getSpentCountCurrentRegistration() {
        try {
            Outputs outputs = OutputsApi.getSpentOutputs(KeyPairHolder.base58PublicKey());
            return outputs.getOutput().size();
        } catch (IOException e) {
            return 0;
        }
    }

    @Override
    public double getSpentAvgWeightCurrentRegistration() {

        return 0;
    }
}
