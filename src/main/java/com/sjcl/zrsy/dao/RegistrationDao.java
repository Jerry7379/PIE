package com.sjcl.zrsy.dao;

import com.sjcl.zrsy.domain.po.Registration;
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

    public Registration getLoginByRegistrationId(String registrationId){
        List<Registration> registrations =jdbcTemplate.query("select * from registration where registration_id= ?", new Object[]{registrationId}, new RowMapper<Registration>(){
            @Override
            public Registration mapRow(ResultSet resultSet, int i) throws SQLException {
                Registration user = new Registration();
                user.setPassword(resultSet.getString("passwd"));
                user.setType(resultSet.getString("types"));
                return user;
            }
        });
        if (registrations != null && registrations.size() > 0) {
            return registrations.get(0);
        } else {
            return null;
        }
    }

    public boolean insertRegistration(Registration registration){
        try {
            jdbcTemplate.update("insert into registration (registration_id,types,email,picture,name,location,legal_rep,capital,date_establishment) value(?,?,?,?,?,?,?,?,?)",
                    registration.getRegistrationId(),
                    registration.getType(),
                    registration.getEmail(),
                    registration.getPicture(),
                    registration.getName(),
                    registration.getLocation(),
                    registration.getLegal_rep(),
                    registration.getCapital(),
                    registration.getDestablishment()
            );

            return true;
        }catch(Exception e) {
            return false;
        }

    }


}
