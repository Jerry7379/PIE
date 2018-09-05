package com.sjcl.zrsy.domain.dto;


import org.hibernate.validator.constraints.Length;

public class MarketReception {
    @Length(min = 13,max = 13,message ="猪ID长度为13")
    private String id;
    @Length(min = 6,max = 6,message = "超市ID长度为6")
    private String marketId;

    public MarketReception(String id, String marketId) {
        this.id = id;
        this.marketId = marketId;
    }

    public MarketReception() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }
}
