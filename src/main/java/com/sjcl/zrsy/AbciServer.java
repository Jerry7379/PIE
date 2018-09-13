package com.sjcl.zrsy;

import com.github.jtendermint.jabci.api.ABCIAPI;
import com.github.jtendermint.jabci.api.CodeType;
import com.github.jtendermint.jabci.socket.TSocket;
import com.github.jtendermint.jabci.types.*;
import com.google.protobuf.ByteString;

public class AbciServer implements ABCIAPI {
    private ByteString lastBlockAppHash;
    private long lastBlockHeight;

    private TSocket socket;

    public AbciServer() throws InterruptedException {
        socket = new TSocket();
        socket.registerListener(this);

        Thread t = new Thread(() -> socket.start(26658));
        t.setName("pie abci application");
        t.start();
        while (true) {
            Thread.sleep(1000L);
        }

    }

    public static void main(String[] args) throws InterruptedException {
        new AbciServer();
    }

    @Override
    public ResponseCheckTx requestCheckTx(RequestCheckTx req) {
        return ResponseCheckTx.newBuilder().setCode(CodeType.OK).build();
    }

    @Override
    public ResponseBeginBlock requestBeginBlock(RequestBeginBlock req) {
        return ResponseBeginBlock.newBuilder().build();
    }

    @Override
    public ResponseDeliverTx receivedDeliverTx(RequestDeliverTx req) {
        return ResponseDeliverTx.newBuilder().setCode(CodeType.OK).build();
    }

    @Override
    public ResponseEndBlock requestEndBlock(RequestEndBlock req) {
        return ResponseEndBlock.newBuilder().build();
    }

    @Override
    public ResponseCommit requestCommit(RequestCommit requestCommit) {
        return ResponseCommit.newBuilder().build();
    }

    @Override
    public ResponseEcho requestEcho(RequestEcho req) {
        String message = req.getMessage();
        return ResponseEcho.newBuilder().setMessage(message).build();
    }

    @Override
    public ResponseInfo requestInfo(RequestInfo req) {
        //TODO info
        return ResponseInfo.newBuilder()
//                .setLastBlockHeight()
//                .setLastBlockAppHash()
                .build();
    }

    @Override
    public ResponseInitChain requestInitChain(RequestInitChain req) {
        //TODO
        return ResponseInitChain.newBuilder().build();
    }

    // don't modify
    @Override
    public ResponseQuery requestQuery(RequestQuery req) {
        return ResponseQuery.newBuilder().setCode(CodeType.OK).build();
    }

    @Override
    public ResponseFlush requestFlush(RequestFlush reqfl) {
        return ResponseFlush.newBuilder().build();
    }

    @Override
    public ResponseSetOption requestSetOption(RequestSetOption req) {
        return ResponseSetOption.newBuilder().build();
    }
}
