//package com.sjcl.zrsy.web3j;
//
//public class AccountTest {
//
//    public static void main(String args[]) {
//        getBalance();
//    }
//
//    public static void getBalance(){
//        Account account = new Account();
//        BigInteger ba = account.getBalance("0xcee1086eabd4cac10f6658eeffcdc66ad7565450");
//        System.out.print(ba);
//    }
//
//    public static  void queryAccount(){
//        Account account = new Account();
//        List<String> accounts = account.getAccountlist();
//        for(String accountId:accounts){
//            System.out.println(accountId);
//        }
//
//    }
//
//    public void createAccount(){
//        Account account = new Account();
//        AccountInfo accountInfo = new AccountInfo();
//        accountInfo.setPhone("229787499");
//        accountInfo.setAddress("世宁大厦");
//        accountInfo.setSchool("buaa");
//        accountInfo.setUserName("lzh");
//        String accountId = account.createAccount("lzh","123456",accountInfo);
//        System.out.println("注册账户成功:"+accountId);
////        PersonalAccountsInfo.AccountsInfo accountsInfo = account.getAccountInfo("0xad7bbca86e02e503076b06931e05938e51e49fb9");
////        System.out.println(accountsInfo.toString());
//    }
//}
