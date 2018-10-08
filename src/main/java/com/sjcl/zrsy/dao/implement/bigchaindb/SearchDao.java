package com.sjcl.zrsy.dao.implement.bigchaindb;

import com.sjcl.zrsy.bigchaindb.BigchaindbUtil;
import com.sjcl.zrsy.dao.IOperationDao;
import com.sjcl.zrsy.domain.dto.SearchId;
import com.sjcl.zrsy.domain.po.Operation;
import com.sjcl.zrsy.domain.po.TraceabilityIdcard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SearchDao implements com.sjcl.zrsy.dao.ISearchDao {

    @Autowired
    private IOperationDao operationDao;

    @Override
    public SearchId select(String id) {
        String sql = "SELECT\n" +
                        "    id,\n" +
                        "    birthday,\n" +
                        "    breed,\n" +
                        "    gender,\n" +
                        "    isacid,\n" +
                        "    ischeck,\n" +
                        "    farm_id,\n" +
                        "    farm_location,\n" +
                        "    farm_name,\n" +
                        "    traceability_idcard.breeder_id,\n" +
                        "    slaughterhouse_id,\n" +
                        "    slaughterhouse_location,\n" +
                        "    slaughterhouse_name,\n" +
                        "    traceability_idcard.checker_id,\n" +
                        "    traceability_idcard.acider_id,\n" +
                        "    logistics_id,\n" +
                        "    logistics_name,\n" +
                        "    logistics_location,\n" +
                        "    supermarket_id,\n" +
                        "    supermarket_name,\n" +
                        "    supermarket_location,\n" +
                        "    salesperson_id\n" +
                        "FROM\n" +
                        "    traceability_idcard\n" +
                        "    LEFT JOIN pig_farm using(farm_id)\n" +
                        "    LEFT JOIN pig_slaughterhouse using(slaughterhouse_id)\n" +
                        "    LEFT JOIN pig_logistics using(logistics_id)\n" +
                        "    LEFT JOIN pig_supermarket using(supermarket_id)\n" +
                        "WHERE\n" +
                        "    id = ?";


        if (BigchaindbUtil.assetIsExist(id, String.class)) {

            SearchId searchId = getSearchId(id);
            searchId.setOperations(searchOperation(id));
            return searchId;
        } else {
            return null;
        }

    }

    private SearchId getSearchId(String id) {
        TraceabilityIdcard traceabilityIdcard = BigchaindbUtil.getWholeMetaData(id);

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
    }

    private List<Operation> searchOperation(String id) {
        return operationDao.findallOperationByPigid(id);
    }
}
