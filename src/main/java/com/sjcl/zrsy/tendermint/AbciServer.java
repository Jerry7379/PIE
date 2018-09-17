package com.sjcl.zrsy.tendermint;

import com.alibaba.fastjson.JSON;
import com.github.jtendermint.crypto.ByteUtil;
import com.github.jtendermint.jabci.api.ABCIAPI;
import com.github.jtendermint.jabci.api.CodeType;
import com.github.jtendermint.jabci.socket.TSocket;
import com.github.jtendermint.jabci.types.*;
import com.google.protobuf.ByteString;
import com.sjcl.zrsy.action.SearchAction;
import com.sjcl.zrsy.dao.AppInfoDao;
import com.sjcl.zrsy.domain.dto.RestfulResult;
import com.sjcl.zrsy.domain.po.AppInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

@Component
public class AbciServer implements ABCIAPI, ApplicationListener<ContextRefreshedEvent> {
    private AppInfo appInfo;


    @Autowired
    private ActionMapping actionMapping;

    @Autowired
    private SearchAction searchAction;

    @Autowired
    private AppInfoDao appInfoDao;



    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.actionMapping.registet(event.getApplicationContext());
        this.appInfo = appInfoDao.load();


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
    }

    @Override
    public ResponseCheckTx requestCheckTx(RequestCheckTx req) {
        //TODO 注册
        //TODO 验证签名

        return ResponseCheckTx.newBuilder().setCode(CodeType.OK).build();
    }



    private List<Transaction> txsInABlock = new LinkedList<>();

    @Override
    public ResponseBeginBlock requestBeginBlock(RequestBeginBlock req) {
        txsInABlock.clear();
        return ResponseBeginBlock.newBuilder().build();
    }

    @Override
    public ResponseDeliverTx receivedDeliverTx(RequestDeliverTx req) {
        String txStr =  req.getTx().toStringUtf8();
        Transaction tx = JSON.parseObject(txStr, Transaction.class);
        txsInABlock.add(tx);
        try {
            Object ret = processTx(tx);
            String retStr = JSON.toJSONString(ret);
            return ResponseDeliverTx.newBuilder()
                    .setCode(CodeType.OK)
                    .setData(ByteString.copyFrom(retStr, Charset.forName("utf-8")))
                    .build();
        } catch (Exception e) {
            return ResponseDeliverTx.newBuilder()
                    .setCode(CodeType.OK)
                    .setData(ByteString.copyFrom(e.getMessage(), Charset.forName("utf-8")))
                    .build();
        }


    }

    private Object processTx(Transaction tx) {
        String actionName = tx.getAction();
        String paramStr = tx.getParam();
        Action action = actionMapping.getAction(actionName);
        return action.act(paramStr);
    }

    @Override
    public ResponseCommit requestCommit(RequestCommit requestCommit) {
        int blockHashCode = txsInABlock.hashCode();
        ByteString hash = ByteString.copyFrom(ByteUtil.toBytes(blockHashCode));

        this.appInfo.incrementLastBlockHeight();
        this.appInfo.setLastBlockAppHash(hash);

        appInfoDao.save(appInfo);

        return ResponseCommit.newBuilder().setData(hash).build();
    }

    @Override
    public ResponseEcho requestEcho(RequestEcho req) {
        String message = req.getMessage();
        return ResponseEcho.newBuilder().setMessage(message).build();
    }

    @Override
    public ResponseInfo requestInfo(RequestInfo req) {
        return ResponseInfo.newBuilder()
                .setLastBlockHeight(appInfo.getLastBlockHeight())
                .setLastBlockAppHash(appInfo.getLastBlockAppHash())
                .build();
    }


    @Override
    public ResponseQuery requestQuery(RequestQuery req) {
        String id = req.getData().toStringUtf8();
        RestfulResult result = searchAction.get(id);
        String value = JSON.toJSONString(result);
        return ResponseQuery.newBuilder()
                .setCode(CodeType.OK)
                .setValue(ByteString.copyFrom(value, Charset.forName("utf-8")))
                .build();
    }

    // don't modify
    @Override
    public ResponseEndBlock requestEndBlock(RequestEndBlock req) {
        return ResponseEndBlock.newBuilder().build();
    }

    @Override
    public ResponseFlush requestFlush(RequestFlush reqfl) {
        return ResponseFlush.newBuilder().build();
    }

    @Override
    public ResponseSetOption requestSetOption(RequestSetOption req) {
        return ResponseSetOption.newBuilder().build();
    }

    @Override
    public ResponseInitChain requestInitChain(RequestInitChain req) {
        return ResponseInitChain.newBuilder().build();
    }
}
