package com.sjcl.zrsy.domain.po;

import com.google.protobuf.ByteString;

public class AppInfo {
    private long lastBlockHeight;
    private ByteString lastBlockAppHash;

    public AppInfo() {
        this.lastBlockHeight = 0;
        this.lastBlockAppHash = ByteString.EMPTY;
    }

    public AppInfo(long lastBlockHeight, ByteString lastBlockAppHash) {
        this.lastBlockHeight = lastBlockHeight;
        this.lastBlockAppHash = lastBlockAppHash;
    }

    public long getLastBlockHeight() {
        return lastBlockHeight;
    }

    public void incrementLastBlockHeight() {
        this.lastBlockHeight = this.lastBlockHeight + 1;
    }

    public ByteString getLastBlockAppHash() {
        return lastBlockAppHash;
    }

    public void setLastBlockAppHash(ByteString lastBlockAppHash) {
        this.lastBlockAppHash = lastBlockAppHash;
    }
}
