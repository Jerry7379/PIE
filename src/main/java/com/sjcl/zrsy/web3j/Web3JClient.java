//package com.sjcl.zrsy.web3j;
//
//public class Web3JClient {
//
//    private static String ip = "http://ip地址:8545/";
//
//    private Web3JClient(){}
//
//    private volatile static Web3j web3j;
//
//    public static Web3j getClient(){
//        if(web3j==null){
//            synchronized (Web3JClient.class){
//                if(web3j==null){
//                    web3j = Web3j.build(new HttpService(ip));
//                }
//            }
//        }
//        return web3j;
//    }
//}
