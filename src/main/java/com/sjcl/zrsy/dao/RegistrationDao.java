package com.sjcl.zrsy.dao;

import com.sjcl.zrsy.domain.po.RoleRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class RegistrationDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<String> getLoginByRegistrationId(String registrationId){
        List<String> passwords=jdbcTemplate.query("select * from registration where registration_id= ?", new Object[]{registrationId}, new RowMapper<String>(){
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                String u=resultSet.getString("passwd")+";"+resultSet.getString("types");
                return u;
            }
        });
        return passwords;
    }

    public boolean insertRegistration(RoleRegistration roleRegistration){
        try {
            jdbcTemplate.update("insert into registration (registration_id,types,email,picture,name,location,legal_rep,capital,date_establishment) value(?,?,?,?,?,?,?,?,?)",
                    roleRegistration.getRegistrationId(), roleRegistration.getType(), roleRegistration.getEmail(), roleRegistration.getPicture(), roleRegistration.getName(), roleRegistration.getLocation(), roleRegistration.getLegal_rep(), roleRegistration.getCapital(), roleRegistration.getDestablishment());
            return true;
        }catch(Exception e) {
            return false;
        }

    }


}
