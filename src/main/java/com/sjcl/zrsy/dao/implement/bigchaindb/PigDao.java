package com.sjcl.zrsy.dao.implement.bigchaindb;

import com.bigchaindb.api.OutputsApi;
import com.bigchaindb.api.TransactionsApi;
import com.bigchaindb.model.Asset;
import com.bigchaindb.model.Output;
import com.bigchaindb.model.Outputs;
import com.bigchaindb.model.Transaction;
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

        try {
            Outputs outputs = OutputsApi.getSpentOutputs(KeyPairHolder.base58PublicKey());
            for (Output output : outputs.getOutput()) {
                String transactionId = output.getTransactionId();
                Transaction transaction = TransactionsApi.getTransactionById(transactionId);
                Asset asset = transaction.getAsset();
                asset.getId();

            }
            return 0;
        } catch (IOException e) {
            return 0;
        }
    }
}
