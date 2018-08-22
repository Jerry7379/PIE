package com.sjcl.zrsy.dao;

import com.sjcl.zrsy.domain.Registration;
import com.sjcl.zrsy.generated.Greeter;
import org.springframework.stereotype.Component;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

import static java.math.BigInteger.ONE;

@Component
public class ChainDao {



    public String chaintest() throws Exception {
        // We start by creating a new web3j instance to connect to remote nodes on the network.
        // Note: if using web3j Android, use Web3jFactory.build(...
        //创建一个连接，与ethereum相连
        Web3j web3j = Web3j.build(new HttpService(
                "http://172.16.91.128:8545"));  // 以太坊的url，默认端口为：8545
        System.out.println("Connected to Ethereum client version: "
                + web3j.web3ClientVersion().send().getWeb3ClientVersion());//得到Ethereum client的版本信息

        // We then need to load our Ethereum wallet file
        // FIXME: Generate a new wallet file using the web3j command line tools https://docs.web3j.io/command_line.html
        //输入转账的账户密码和钱包文件的地址
        Credentials credentials =
                WalletUtils.loadCredentials(
                        //账户密码
                        "78787878",
                        //账户地址
                        "UTC--2018-08-21T03-31-25.297053329Z--450b80ea636c0b1ccf6ebc12517137fd5e08ef8f");
        System.out.println("Credentials loaded");

        // FIXME: Request some Ether for the Rinkeby test network at https://www.rinkeby.io/#faucet
        System.out.println("Sending 1 Wei ("
                + Convert.fromWei("1", Convert.Unit.ETHER).toPlainString() + " Ether)");
        TransactionReceipt transferReceipt = Transfer.sendFunds(
                web3j, credentials,
                "0x445d6b004dc958944940b46ee1a00c8d3f505d06",  // 被转账的地址 password:12345678
                BigDecimal.ONE, Convert.Unit.WEI)  // 1 wei = 10^-18 Ether
                .send();
        System.out.println("Transaction complete, view it at https://rinkeby.etherscan.io/tx/"
                + transferReceipt.getTransactionHash());

        // Now lets deploy a smart contract
        System.out.println("Deploying smart contract");
        Greeter contract=new Greeter("0x4d1b2ceee91650eda30845a6f7848a14b9545ac9",web3j,credentials, BigInteger.ONE,BigInteger.TEN) ;

        String contractAddress = contract.getContractAddress();
        System.out.println("Smart contract deployed to address " + contractAddress);
        System.out.println("View contract at https://rinkeby.etherscan.io/address/" + contractAddress);

        System.out.println("Value stored in remote smart contract: " + contract.greet().send());

        // Lets modify the value in our smart contract
        TransactionReceipt transactionReceipt = contract.newGreeting("Well hello again").send();

        System.out.println("New value stored in remote smart contract: " + contract.greet().send());
        //return contract.greet().send();
        // Events enable us to log specific events happening during the execution of our smart
        // contract to the blockchain. Index events cannot be logged in their entirety.
        // For Strings and arrays, the hash of values is provided, not the original value.
        // For further information, refer to https://docs.web3j.io/filters.html#filters-and-events
        for (Greeter.ModifiedEventResponse event : contract.getModifiedEvents(transactionReceipt)) {
            System.out.println("Modify event fired, previous value: " + event.oldGreeting
                    + ", new value: " + event.newGreeting);
            System.out.println("Indexed event previous value: " + Numeric.toHexString(event.oldGreetingIdx)
                    + ", new value: " + Numeric.toHexString(event.newGreetingIdx));
        }
        return contract.greet().send();

    }
}
