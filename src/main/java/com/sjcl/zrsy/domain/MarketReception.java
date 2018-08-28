package com.sjcl.zrsy.domain;

/**
 *
 */
public class MarketReception {
    private String id;
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
