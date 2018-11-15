package com.sjcl.zrsy.controller;

import com.bigchaindb.builders.BigchainDbConfigBuilder;
import com.sjcl.zrsy.bigchaindb.KeyPairHolder;
import com.sjcl.zrsy.bigchaindb.KeyPairService;
import com.sjcl.zrsy.dao.implement.bigchaindb.PigDao;
import com.sjcl.zrsy.dao.implement.bigchaindb.TraceabillityIdcardDao;
import com.sjcl.zrsy.domain.po.TraceabilityIdcard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.security.KeyPair;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class FarmControllerTest {
    private static Date randomDate(String beginDate, String  endDate ){
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start = format.parse(beginDate);//构造开始日期
            Date end = format.parse(endDate);//构造结束日期
            //getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。
            if(start.getTime() >= end.getTime()){
                return null;
            }
            long date = random(start.getTime(),end.getTime());
            return new Date(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private static long random(long begin,long end){
        long rtn = begin + (long)(Math.random() * (end - begin));
        //如果返回的是开始时间和结束时间,则递归调用本函数查找随机值
        if(rtn == begin || rtn == end){
            return random(begin,end);
        }
        return rtn;
    }
    
    ;

    public static void main(String[] args) {

        BigchainDbConfigBuilder
                .baseUrl("http://127.0.0.1:9984")
                .setup();

       TraceabillityIdcardDao idcardDao = null;
       KeyPairService keyPairService=new KeyPairService();
       //KeyPairHolder.setKeyPair(keyPairService.get("123456"));
       for(int i=0;i<1000;i++){
           TraceabilityIdcard idcard=new TraceabilityIdcard();

           String a="223";
           a=a+(int)Math.random()*100000+(int)Math.random()*100000;
           idcard.setId(a);
           idcard.setBreederId("110226199509031418");
           idcard.setBirthday( new java.sql.Date(randomDate("2018-07-01","2018-11-11").getTime()));
           idcard.setBirthweight(12);
           if(i%4==0){
               idcard.setBreed("品种1");
               idcard.setGender("雌");
           }else if(i%4==1){
               idcard.setBreed("品种2");
               idcard.setGender("雄");
           }else if(i%4==2){
               idcard.setBreed("品种3");
               idcard.setGender("雌");
           }else{
               idcard.setBreed("品种4");
               idcard.setGender("雄");
           }
           idcardDao.insert(idcard);

       }
    }
}
