package com.sjcl.zrsy.dao.implement.bigchaindb;

import com.bigchaindb.api.OutputsApi;
import com.bigchaindb.api.TransactionsApi;
import com.bigchaindb.model.Asset;
import com.bigchaindb.model.Output;
import com.bigchaindb.model.Outputs;
import com.bigchaindb.model.Transaction;
import com.google.gson.internal.LinkedTreeMap;
import com.sjcl.zrsy.bigchaindb.BigchaindbUtil;
import com.sjcl.zrsy.bigchaindb.KeyPairHolder;
import com.sjcl.zrsy.dao.IOperationDao;
import com.sjcl.zrsy.dao.IPigDao;
import com.sjcl.zrsy.dao.ITraceabilityIdcardDao;
import com.sjcl.zrsy.domain.po.Operation;
import com.sjcl.zrsy.domain.po.TraceabilityIdcard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class PigDao implements IPigDao {

    @Autowired
    private IOperationDao operationDao;

    @Autowired
    private ITraceabilityIdcardDao traceabilityIdcardDao;


    @Override
    public int getUnspentCountCurrentRegistration() {
        try {
            return getUnspentPigIds().size();
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int getSpentCountCurrentRegistration() {
        try {
            return getSpentPigIds().size();
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public double getSpentAvgWeightCurrentRegistration() {

        try {
            Set<String> pigIds = getSpentPigIds();

            double totalWeight = 0;
            for (String pigId : pigIds) {
                List<Operation> operations = operationDao.findallOperationByPigid(pigId);

                for (Operation operation : operations) {
                    if ("出栏体重".equals(operation.getOperation())) {
                        double weight = Double.parseDouble(operation.getContent());
                        totalWeight += weight;
                        break;
                    }
                }
            }
            return totalWeight / pigIds.size();
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int getBirthCountCurrentRegistration(LocalDate start, LocalDate end) {

        try {
            int count = 0;

            Set<String> pigIds = getAllPigIds();
            for (String pigId : pigIds) {
                TraceabilityIdcard idcard = BigchaindbUtil.getWholeMetaData(pigId, TraceabilityIdcard.class);
                LocalDate birthDay =  getBirthDay(idcard);
                if (birthDay.isAfter(start) && birthDay.isBefore(end)) {
                    count++;
                }
            }
            return count;

        } catch (Exception e) {
            return 0;
        }

    }

    //TODO
    private LocalDate getBirthDay(TraceabilityIdcard idcard) {
        return null;
    }

    @Override
    public double getSpentAvgWeightCurrentRegistration(LocalDate start, LocalDate end) {
        try {
            Set<String> pigIds = getSpentPigIds();
            int count = 0;
            double totalWeight = 0;

            for (String pigId : pigIds) {

                List<Operation> operations = operationDao.findallOperationByPigid(pigId);

                for (Operation operation : operations) {
                    LocalDate operationTime = LocalDate.parse(operation.getTime());
                    if ((operationTime.isAfter(start) && operationTime.isAfter(end))
                            && "出栏体重".equals(operation.getOperation())) {
                        double weight = Double.parseDouble(operation.getContent());
                        totalWeight += weight;
                        count += 1;
                        break;
                    }
                }

            }

            return totalWeight / count;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int getSpentCountCurrentRegistration(LocalDate start, LocalDate end) {
        try {
            Set<String> pigIds = getSpentPigIds();
            int count = 0;

            for (String pigId : pigIds) {

                List<Operation> operations = operationDao.findallOperationByPigid(pigId);

                for (Operation operation : operations) {
                    LocalDate operationTime = LocalDate.parse(operation.getTime());
                    if ((operationTime.isAfter(start) && operationTime.isAfter(end))
                            && "出栏体重".equals(operation.getOperation())) {
                        count += 1;
                        break;
                    }
                }

            }

            return count;
        } catch (Exception e) {
            return 0;
        }
    }

    private Set<String> getSpentPigIds() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Outputs outputs = OutputsApi.getSpentOutputs(KeyPairHolder.base58PublicKey());
        return extractPigId(outputs);
    }
    private Set<String> getUnspentPigIds() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Outputs outputs = OutputsApi.getUnspentOutputs(KeyPairHolder.base58PublicKey());
        return extractPigId(outputs);
    }
    private Set<String> getAllPigIds() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Outputs outputs = OutputsApi.getOutputs(KeyPairHolder.base58PublicKey());
        return extractPigId(outputs);
    }

    private Set<String> extractPigId(Outputs outputs) throws IOException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Set<String> ids = new HashSet<>();
        for (Output output : outputs.getOutput()) {
            String transactionId = output.getTransactionId();
            Transaction transaction = TransactionsApi.getTransactionById(transactionId);
            Object asset = BigchaindbUtil.bigchaindbDataToBean((LinkedTreeMap) transaction.getAsset().getData());
            if (asset instanceof String && TraceabillityIdcardDao.ASSET_OBJECT.equals(asset)) {
                ids.add(transaction.getAsset().getId());
            }
        }
        return ids;
    }

}
