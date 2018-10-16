package com.sjcl.zrsy.dao.implement.bigchaindb;

import com.bigchaindb.api.AssetsApi;
import com.bigchaindb.model.Asset;
import com.bigchaindb.model.Assets;
import com.sjcl.zrsy.bigchaindb.BigchaindbUtil;
import com.sjcl.zrsy.domain.po.Registration;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

@Repository
public class RegistrationDao implements com.sjcl.zrsy.dao.IRegistrationDao {

    @Override
    public Registration getLoginByRegistrationId(String registrationId){
        try {
            Assets assets = AssetsApi.getAssetsWithLimit(registrationId, "1");
            if (assets != null && assets.size() >= 1) {
                Asset asset = assets.getAssets().get(0);
                Object actualAsset = BigchaindbUtil.getAsset(asset.getId());
                if (actualAsset instanceof Registration
                        && StringUtils.equals(registrationId, ((Registration) actualAsset).getRegistrationId())) {
                    Registration registration = (Registration) actualAsset;
                    registration.setPublicKey(BigchaindbUtil.getOwner(asset.getId()));
                    return (Registration) actualAsset;
                }
            }

            return null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean insertRegistration(Registration registration){
        try {
            String assetId = BigchaindbUtil.createAsset(registration);
            return assetId != null;
        } catch (Exception e) {
            return false;
        }

    }


}
