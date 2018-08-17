package com.sjcl.zrsy.controller;


import com.sjcl.zrsy.domain.Registration;
import com.sjcl.zrsy.service.IPigService;
import com.sjcl.zrsy.domain.Pig_Birth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

//    @Autowired
//    private JdbcTemplate jdbcTemplate;

    @Autowired
    IPigService insertpig;

    @GetMapping("/hello")  //注解，只是访问此方法的url，在浏览器中输入http://localhost:8080/hello
    public String hello() {
        return " hello";
    }




    @PostMapping("/insertsamllpig")
    public void insertsamllpig(@RequestBody Pig_Birth sm)
    {
         //return jdbcTemplate.update("insert into Pig_Birth (id,birthday,sort,sex,weight) values  (?,?,?,?,?)", sm.getId(),sm.getBirthday(),sm.getSort(),sm.getSex(),sm.getWeight());
        insertpig.insertpig(sm);
    }

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
    @PostMapping("/test")
    public void test(@RequestBody Registration test)
    {
         insertpig.registration(test);
    }
}
