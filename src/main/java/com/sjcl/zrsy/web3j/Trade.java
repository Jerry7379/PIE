//package com.sjcl.zrsy.web3j;
//
//public class Trade {
//
//    private static final Logger logger = LoggerFactory.getLogger(Trade.class);
//
//    private static BigInteger nonce = new BigInteger("0");
//
//    private static BigInteger gasPrice = new BigInteger("1");
//
//    private static BigInteger gasLimit = new BigInteger("50");
//
//    private Parity parity = ParityClient.getParity();
//
//    public boolean trasfer(String accountId,String passsword,String toAccountId, BigDecimal amount)  {
//
//        Transaction transaction = Transaction.createEtherTransaction(accountId,null,null,null,toAccountId,amount.toBigInteger());
//        try{
//            EthSendTransaction ethSendTransaction =parity.personalSignAndSendTransaction(transaction,passsword).send();
//            if(ethSendTransaction!=null){
//                String tradeHash = ethSendTransaction.getTransactionHash();
//                logger.info("账户:[{}]转账到账户:[{}],交易hash:[{}]",accountId,toAccountId,tradeHash);
//            }
//        }catch (Exception e){
//            logger.error("账户:[{}]交易失败!",accountId,e);
//        }
//        return false;
//    }
//
//}