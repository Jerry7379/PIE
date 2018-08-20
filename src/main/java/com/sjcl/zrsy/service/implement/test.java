package com.sjcl.zrsy.service.implement;


import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Service
public class test {
    public String getTime() {
       // System.out.println("获取时间" + new Date());

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss ");
        System.out.println("格式化输出：" + sdf.format(d));

        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        //System.out.println("Asia/Shanghai:" + sdf.format(d));

        return "格式化输出：" + sdf.format(d) + '\n' + "Asia/Shanghai:" + sdf.format(d);
    }
}