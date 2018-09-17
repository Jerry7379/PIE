package com.sjcl.zrsy.tendermint;

import com.alibaba.fastjson.JSON;
import com.github.jtendermint.jabci.api.ABCIAPI;
import com.github.jtendermint.jabci.api.CodeType;
import com.github.jtendermint.jabci.socket.TSocket;
import com.github.jtendermint.jabci.types.*;
import com.google.protobuf.ByteString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import java.nio.charset.Charset;

@Component
public class AbciServer implements ABCIAPI, ApplicationListener<ContextRefreshedEvent> {
    private ByteString lastBlockAppHash;
    private long lastBlockHeight;


    @Autowired
    private ActionMapping actionMapping;

    public AbciServer() throws InterruptedException {
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        actionMapping.registet(event.getApplicationContext());

        TSocket socket = new TSocket();
        socket.registerListener(this);

        Thread t = new Thread(() -> socket.start(26658));
        t.setName("pie abci application");
        t.start();
        while (true) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new AbciServer();
    }

    @Override
    public ResponseCheckTx requestCheckTx(RequestCheckTx req) {
        //TODO 验证签名
        return ResponseCheckTx.newBuilder().setCode(CodeType.OK).build();
    }

    @Override
    public ResponseBeginBlock requestBeginBlock(RequestBeginBlock req) {
        return ResponseBeginBlock.newBuilder().build();
    }

    @Override
    public ResponseDeliverTx receivedDeliverTx(RequestDeliverTx req) {
        String txStr =  req.getTx().toStringUtf8();
        Transaction tx = JSON.parseObject(txStr, Transaction.class);
        Object ret = processTx(tx);
        String retStr = JSON.toJSONString(ret);
        return ResponseDeliverTx.newBuilder()
                .setCode(CodeType.OK)
                .setData(ByteString.copyFrom(retStr, Charset.forName("utf-8")))
                .build();
    }

    private Object processTx(Transaction tx) {
        String actionName = tx.getAction();
        String paramStr = tx.getParam();
        Action action = actionMapping.getAction(actionName);
        return action.act(paramStr);
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
