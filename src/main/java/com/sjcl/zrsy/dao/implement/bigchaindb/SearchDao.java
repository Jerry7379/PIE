package com.sjcl.zrsy.dao.implement.bigchaindb;

import com.sjcl.zrsy.bigchaindb.BigchaindbUtil;
import com.sjcl.zrsy.dao.IOperationDao;
import com.sjcl.zrsy.dao.IRegistrationDao;
import com.sjcl.zrsy.domain.dto.SearchId;
import com.sjcl.zrsy.domain.po.Operation;
import com.sjcl.zrsy.domain.po.Registration;
import com.sjcl.zrsy.domain.po.TraceabilityIdcard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SearchDao implements com.sjcl.zrsy.dao.ISearchDao {

    @Autowired
    private IOperationDao operationDao;

    @Autowired
    private IRegistrationDao registrationDao;

    @Override
    public SearchId select(String id) {
        if (BigchaindbUtil.assetIsExist(id, String.class)) {
            SearchId searchId = getSearchId(id);
            searchId.setOperations(searchOperation(id));
            return searchId;
        } else {
            return null;
        }

    }

    private SearchId getSearchId(String id) {
        try {
            TraceabilityIdcard traceabilityIdcard = BigchaindbUtil.getWholeMetaData(id, TraceabilityIdcard.class);
            traceabilityIdcard.setId(id);
            if (traceabilityIdcard == null) {
                return null;
            }

            SearchId searchId = new SearchId();
            searchId.setId(traceabilityIdcard.getId());
            searchId.setBirthday(traceabilityIdcard.getBirthday().toString());
            searchId.setBreed(traceabilityIdcard.getBreed());
            searchId.setGender(traceabilityIdcard.getGender());
            searchId.setIsacid(String.valueOf(traceabilityIdcard.getIsacid()));
            searchId.setIscheck(String.valueOf(traceabilityIdcard.getIscheck()));

            Registration farm = registrationDao.getRegistrationByRegistrationId(traceabilityIdcard.getFarmId());
            searchId.setFarmId(traceabilityIdcard.getFarmId());
            searchId.setFarmLocation(farm.getLocation());
            searchId.setFarmName(farm.getName());

            searchId.setSlaughterhouseId(traceabilityIdcard.getSlaughterhouseId());
            Registration slaughterhouse = registrationDao.getRegistrationByRegistrationId(traceabilityIdcard.getSlaughterhouseId());
            if (slaughterhouse != null) {
                searchId.setSlaughterhouseLocation(slaughterhouse.getLocation());
                searchId.setSlaughterhouseName(slaughterhouse.getName());
            }

            searchId.setLogisticsId(traceabilityIdcard.getLogisticsId());
            Registration logistics = registrationDao.getRegistrationByRegistrationId(traceabilityIdcard.getLogisticsId());
            if (logistics != null) {
                searchId.setLogisticsName(logistics.getName());
                searchId.setLogisticsLocation(logistics.getLocation());
            }

            searchId.setSupermarketId(traceabilityIdcard.getSupermarketId());
            Registration superMarket = registrationDao.getRegistrationByRegistrationId(traceabilityIdcard.getSupermarketId());
            if (superMarket != null) {
                searchId.setSupermarketName(superMarket.getName());
                searchId.setSupermarketLocation(superMarket.getLocation());
            }

            searchId.setBreederId(traceabilityIdcard.getBreederId());
            searchId.setCheckerId(traceabilityIdcard.getCheckerId());
            searchId.setAciderId(traceabilityIdcard.getAciderId());
//        searchId.setSalespersonId(superMarket.gets);
            return searchId;
        } catch (Exception e) {
            return null;
        }
    }

    private List<Operation> searchOperation(String id) {
        return operationDao.findallOperationByPigid(id);
    }
}
