package com.sjcl.zrsy.dao.implement.bigchaindb;

import com.sjcl.zrsy.bigchaindb.BigchaindbUtil;
import com.sjcl.zrsy.dao.IOperationDao;
import com.sjcl.zrsy.domain.dto.SearchId;
import com.sjcl.zrsy.domain.po.Operation;
import com.sjcl.zrsy.domain.po.TraceabilityIdcard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class SearchDao implements com.sjcl.zrsy.dao.ISearchDao {

    @Autowired
    private IOperationDao operationDao;

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
            SearchId searchId = new SearchId();
            searchId.setId(traceabilityIdcard.getId());
            searchId.setBirthday(traceabilityIdcard.getBirthday().toString());
            searchId.setBreed(traceabilityIdcard.getBreed());
            searchId.setGender(traceabilityIdcard.getGender());
            searchId.setIsacid(String.valueOf(traceabilityIdcard.getIsacid()));
            searchId.setIscheck(String.valueOf(traceabilityIdcard.getIscheck()));
            searchId.setFarmId(traceabilityIdcard.getFarmId());

            searchId.setFarmLocation("farmlocation");
            searchId.setFarmName("farmName");
            searchId.setSlaughterhouseLocation("slaughterhouse_location");
            searchId.setSlaughterhouseName("slaughterhouse_name");
            searchId.setLogisticsName("logistics_name");
            searchId.setLogisticsLocation("logistics_location");
            searchId.setSupermarketName("supermarket_name");
            searchId.setSupermarketLocation("supermarket_location");

            searchId.setBreederId(traceabilityIdcard.getBreederId());
            searchId.setSlaughterhouseId(traceabilityIdcard.getSlaughterhouseId());
            searchId.setCheckerId(traceabilityIdcard.getCheckerId());
            searchId.setAciderId(traceabilityIdcard.getAciderId());
            searchId.setLogisticsId(traceabilityIdcard.getLogisticsId());
            searchId.setSupermarketId(traceabilityIdcard.getSupermarketId());
//        searchId.setSalespersonId(traceabilityIdcard.gets ("salesperson_id"));
            return searchId;
        } catch (Exception e) {
            return null;
        }
    }

    private List<Operation> searchOperation(String id) {
        return operationDao.findallOperationByPigid(id);
    }
}
