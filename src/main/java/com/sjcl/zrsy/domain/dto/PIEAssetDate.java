package com.sjcl.zrsy.domain.dto;

import com.sjcl.zrsy.domain.po.TraceabilityIdcard;

public class PIEAssetDate {
    String type;
    TraceabilityIdcard object;

    public PIEAssetDate(String type, TraceabilityIdcard object) {
        this.type = type;
        this.object = object;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public TraceabilityIdcard getObject() {
        return object;
    }

    public void setObject(TraceabilityIdcard object) {
        this.object = object;
    }
}
