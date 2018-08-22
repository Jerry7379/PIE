package com.sjcl.zrsy.controller;


import com.sjcl.zrsy.domain.PigSlaughterReceiver;
import com.sjcl.zrsy.domain.Registration;
import com.sjcl.zrsy.domain.User;
import com.sjcl.zrsy.service.IPigService;

import com.sjcl.zrsy.service.implement.chainservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class PigController {
    @Autowired
    IPigService newPigService;


    @GetMapping("/hello")  //注解，只是访问此方法的url，在浏览器中输入http://localhost:8080/hello
    public String hello() {
        return " hello";
    }


//    @PostMapping("/insertsamllpig")
//    public void insertsamllpig(@RequestBody Pig_Birth sm)
//    {
//         //return jdbcTemplate.update("insert into Pig_Birth (id,birthday,sort,sex,weight) values  (?,?,?,?,?)", sm.getId(),sm.getBirthday(),sm.getSort(),sm.getSex(),sm.getWeight());
//        insertpig.insertpig(sm);
//    }

//    @GetMapping("/getAll")
//    public List<piglife> getAll() {
//        return jdbcTemplate.query("select * from piglife ", new RowMapper<piglife>() {
//            @Override
//            public piglife mapRow(ResultSet resultSet, int i) throws SQLException {
//                piglife u = new piglife();
//                u.setId(resultSet.getString("id"));
//                u.setOperation(resultSet.getString("operation"));
//                u.setContent(resultSet.getString("Content"));
//                u.setRemark(resultSet.getString("remark"));
//                u.setTime(resultSet.getString("time"));
//                return u;
//        }
//        });
//    }
//
//    @GetMapping("/delete")
//    public int delete() {
//        return jdbcTemplate.update("delete from users where username = \"liweibo\"");
//    }

    @PostMapping("/registration") //角色注册
    @ResponseBody
    public String registration(@RequestBody Registration test)
    {
         return newPigService.registration(test);
    }

    @PostMapping("/slaughterreceive")//屠宰检查
    @ResponseBody
    public String  slaughterreceive(@RequestBody PigSlaughterReceiver checker){
        return newPigService.slaughterreceiver(checker);
    }

    @PostMapping("/login")
    @ResponseBody
    public String test(@RequestBody User test, HttpSession session)  {

        //return newPigService.test();
        String info= newPigService.test(test);
        String ib[]=info.split(";");
        if(ib[0].equals("登录成功"))
        {
            session.setAttribute("userInfo", test.getName());
            session.setAttribute("type", ib[1]);
            return "登录成功";
        }
        else
            return info;
    }
}
