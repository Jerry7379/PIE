package com.sjcl.zrsy.burrow;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;

public class BurrowClientTest {

    @Test
    public void testCall() throws IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {


//        ContractFactory contractFactory = new ContractFactory("CFCF8DE0020BB6A7E2006C9EF85658C0CA6CE7B3907AEC7035F1010345B82E5FD48DA91AA446C745AA34D3B9D44DBE604090ED9C611994B7D53C5E8773D2DCBF");
        String privateKey="57BCAD7633DB575799ED111B27923E05E52C932763CE69E1EDC679F6926FBA641BBAD85461AFC93A99F8258CAC64B1080E5922AC286FC341A0F06146051D7516";
        String contractAddress="D7F2946309EFAE78F4D7365F7C34E046492D14F0";
        String abi="[{\n" +
                "    \"constant\":true,\n" +
                "    \"inputs\":[\n" +
                "        {\n" +
                "            \"name\":\"a\",\n" +
                "            \"type\":\"int256\"\n" +
                "        },{\n" +
                "            \"name\":\"b\",\n" +
                "            \"type\":\"int256\"\n" +
                "        }],\n" +
                "    \"name\":\"add\",\n" +
                "    \"outputs\":[{\n" +
                "        \"name\":\"sum\",\n" +
                "        \"type\":\"int256\"\n" +
                "    }],\n" +
                "    \"type\":\"function\"\n" +
                "}]";
        ContractUtil contractUtil=new ContractUtil(privateKey,abi);
        String data=contractUtil.getData("add",BigInteger.valueOf(10),BigInteger.valueOf(12));
        Object o=contractUtil.sendTrancation(contractAddress,data,contractUtil.findAbiFunction("add"));
        System.out.println(o);
//        Caculator caculator = contractFactory.getContractObject(Caculator.class, contractAddress, abi);



//        File file = new File("/opt/working/PIE/src/main/java/com/sjcl/zrsy/solidity/abi/pigCore.abi");//定义一个file对象，用来初始化FileReader
//        FileReader reader = new FileReader(file);//定义一个fileReader对象，用来初始化BufferedReader
//        BufferedReader bReader = new BufferedReader(reader);//new一个BufferedReader对象，将文件内容读取到缓存
//        StringBuilder sb = new StringBuilder();//定义一个字符串缓存，将字符串存放缓存中
//        String s = "";
//        while ((s =bReader.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
//            sb.append(s);//将读取的字符串添加换行符后累加存放在缓存中
//        }
//        bReader.close();
//        PigCore pigCore = contractFactory.getContractObject(PigCore.class, contractAddress,sb.toString());
//        System.out.println(caculator.add(BigInteger.valueOf(12), BigInteger.valueOf(12)));

    }
}
