package com.sjcl.zrsy.dao;

import com.sjcl.zrsy.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class RoleDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public String getall(User user){

        List<String> passwords=jdbcTemplate.query("select * from registration where Registration_id='"+user.getName()+"' ",new RowMapper<String>(){
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                String  u=resultSet.getString("Passwd")+";"+resultSet.getString("Types");
                //String n=resultSet.getString("Types");
                return u;
            }
        });
        String password[]=passwords.get(0).split(";");
        if(!password[0].equals(user.getPassword()))
        {
            return "此账户不存在，请重输入";

        }
        else {
            if (password[0].equals(user.getPassword()))
                return "登录成功;"+password[1];
            else
                return "密码输入错误";
        }
    }

}
