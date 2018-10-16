package com.sjcl.zrsy.burrow;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class BurrowClientTest {

    @Test
    public void testCall() throws IOException {


        ContractFactory contractFactory = new ContractFactory("E3EB51CDF2211153EA11AEBF06BB3C4B50DF6AEDDEF37D9AEA2E1167A1C064B95875F0257A2F6F73C6EEE87AB991712942431281836AAC084B9E529BA3F23F2C");
        String contractAddress="C88AC94C76C0301065001237A0C3E80125EAC181";

        String abi="[{\"constant\": true,\"inputs\": [ {\"name\": \"a\",\"type\": \"int256\" }, {\"name\": \"b\",\"type\": \"int256\"}],\"name\": \"add\",\"outputs\": [{\"name\": \"sum\",\"type\": \"int256\"}],\"type\": \"function\" }]";
        Caculator caculator = contractFactory.getContractObject(Caculator.class, contractAddress, abi);



//        File file = new File("E:\\working\\java\\burrow\\PIE\\src\\main\\java\\com\\sjcl\\zrsy\\myproxy\\add.abi");//定义一个file对象，用来初始化FileReader
//        FileReader reader = new FileReader(file);//定义一个fileReader对象，用来初始化BufferedReader
//        BufferedReader bReader = new BufferedReader(reader);//new一个BufferedReader对象，将文件内容读取到缓存
//        StringBuilder sb = new StringBuilder();//定义一个字符串缓存，将字符串存放缓存中
//        String s = "";
//        while ((s =bReader.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
//            sb.append(s);//将读取的字符串添加换行符后累加存放在缓存中
//        }
//        bReader.close();
//        Caculator caculator = contractFactory.getContractObject(Caculator.class, contractAddress,sb.toString());
        System.out.println(caculator.add(BigInteger.valueOf(15), BigInteger.valueOf(5)));
    }
}
