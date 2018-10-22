package com.sjcl.zrsy.dao.implement.bigchaindb;

import com.bigchaindb.api.OutputsApi;
import com.bigchaindb.api.TransactionsApi;
import com.bigchaindb.model.Asset;
import com.bigchaindb.model.Output;
import com.bigchaindb.model.Outputs;
import com.bigchaindb.model.Transaction;
import com.sjcl.zrsy.bigchaindb.KeyPairHolder;
import com.sjcl.zrsy.dao.IOperationDao;
import com.sjcl.zrsy.dao.IPigDao;
import com.sjcl.zrsy.domain.po.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class PigDao implements IPigDao {

    @Autowired
    private IOperationDao operationDao;


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

            double totalWeight = 0;
            for (Output output : outputs.getOutput()) {
                String transactionId = output.getTransactionId();
                Transaction transaction = TransactionsApi.getTransactionById(transactionId);
                Asset asset = transaction.getAsset();

                List<Operation> operations = operationDao.findallOperationByPigid(asset.getId());

                for (Operation operation : operations) {
                    if ("出栏体重".equals(operation.getOperation())) {
                        double weight = Double.parseDouble(operation.getContent());
                        totalWeight += weight;
                        break;
                    }
                }

            }
            return totalWeight / outputs.getOutput().size();
        } catch (IOException e) {
            return 0;
        }
    }
}
