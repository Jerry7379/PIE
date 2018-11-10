package com.sjcl.zrsy.dao.implement.bigchaindb;

import com.bigchaindb.api.OutputsApi;
import com.bigchaindb.api.TransactionsApi;
import com.bigchaindb.model.Output;
import com.bigchaindb.model.Outputs;
import com.bigchaindb.model.Transaction;
import com.google.gson.internal.LinkedTreeMap;
import com.sjcl.zrsy.bigchaindb.BigchaindbUtil;
import com.sjcl.zrsy.bigchaindb.KeyPairHolder;
import com.sjcl.zrsy.dao.IOperationDao;
import com.sjcl.zrsy.dao.IPigDao;
import com.sjcl.zrsy.dao.ITraceabilityIdcardDao;
import com.sjcl.zrsy.domain.dto.Ratio;
import com.sjcl.zrsy.domain.po.Operation;
import com.sjcl.zrsy.domain.po.TraceabilityIdcard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Repository
public class PigDao implements IPigDao {

    @Autowired
    private IOperationDao operationDao;

    @Autowired
    private ITraceabilityIdcardDao traceabilityIdcardDao;

    /**
     * 获得当前养殖场未交易的猪的个数
     * @return
     */
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

    /**
     * 获得start到end这段时间所有未出栏的猪的个数
     * @param start 开始时间
     * @param end 结束时间
     * @return
     */
    @Override
    public int getBirthCountCurrentRegistration(LocalDate start, LocalDate end) {

        try {
            int count = 0;

            Set<String> pigIds = getAllPigIds();
            for (String pigId : pigIds) {
                TraceabilityIdcard idcard = BigchaindbUtil.getWholeMetaData(BigchaindbUtil.getAssetId(pigId), TraceabilityIdcard.class);
                LocalDate birthDay = getBirthDay(idcard);
                if (birthDay.isAfter(start) && birthDay.isBefore(end)) {
                    count++;
                }
            }
            return count;

        } catch (Exception e) {
            return 0;
        }

    }

    /**
     * 获得猪的出生日期
     * @param idcard
     * @return
     */
    private LocalDate getBirthDay(TraceabilityIdcard idcard) {
        return  idcard.getBirthday().toLocalDate();
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
                    LocalDate operationTime = LocalDate.parse(operation.getTime().substring(0,10));
                    if ((operationTime.isAfter(start) && operationTime.isBefore(end))
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

    /**
     * 本周出栏
     * @param start
     * @param end
     * @return
     */
    @Override
    public int getSpentCountCurrentRegistration(LocalDate start, LocalDate end) {
        try {
            Set<String> pigIds = getSpentPigIds();
            int count = 0;

            for (String pigId : pigIds) {

                List<Operation> operations = operationDao.findallOperationByPigid(pigId);

                for (Operation operation : operations) {

                    LocalDate operationTime = LocalDate.parse(operation.getTime().substring(0,10));
                    if ((operationTime.isAfter(start) && operationTime.isBefore(end))
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

    @Override
    public List<Ratio> getRatio(String category, String scope) {
        try {


            Set<String> pigIds = getAllPigIds();

            Map<String, Integer> map = new HashMap<>();
            for (String pigId : pigIds) {
                TraceabilityIdcard idcard = BigchaindbUtil.getWholeMetaData(pigId, TraceabilityIdcard.class);

                String categoryItem = getCategoryItem(idcard, category);
                Integer categoryItemCount = map.get(categoryItem);


                Integer newCount;
                if (categoryItemCount == null) {
                    newCount = 1;
                } else {
                    newCount = categoryItemCount + 1;
                }
                map.put(categoryItem, newCount);
            }

            return translateToList(map);

        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    private List<Ratio> translateToList(Map<String, Integer> map) {
        List<Ratio> ratios = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String categoryItem = entry.getKey();
            Integer count = entry.getValue();
            Ratio ratio = new Ratio(categoryItem, count);
            ratios.add(ratio);
        }
        return ratios;
    }

    private String getCategoryItem(TraceabilityIdcard idcard, String category) {
        if (RADIO_GENDER.equals(category)) {
            return idcard.getGender();
        } else if (RADIO_VARIETY.equals(category)) {
            return idcard.getBreed();
        } else {
            return "";//TODO 年龄段
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

    /**
     *
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private Set<String> getAllPigIds() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Outputs outputs = OutputsApi.getOutputs(KeyPairHolder.base58PublicKey());
        return extractPigId(outputs);
    }

    private Set<String> extractPigId(Outputs outputs) throws IOException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Set<String> ids = new HashSet<>();
        for (Output output : outputs.getOutput()) {
            String transactionId = output.getTransactionId();
            Transaction transaction = TransactionsApi.getTransactionById(transactionId);
            if(transaction.getAsset().getData()!=null){
                Object asset = BigchaindbUtil.bigchaindbDataToBean((LinkedTreeMap) transaction.getAsset().getData());
                if (asset instanceof HashMap && TraceabillityIdcardDao.ASSET_OBJECT.equals(((HashMap) asset).get("type").toString())) {
                    ids.add(((HashMap) asset).get("pigid").toString());
                }
            }else{
                ;
            }

        }
        return ids;
    }

}
