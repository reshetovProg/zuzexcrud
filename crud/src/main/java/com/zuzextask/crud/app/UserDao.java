package com.zuzextask.crud.app;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserDao {
    private final NamedParameterJdbcTemplate template;

    public UserDao(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    public long createUser(User user){
        String sql = "INSERT INTO userdb (user_name, age, password) " +
                "VALUES  (:user_name, :age, :password) RETURNING ID";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("user_name", user.getUser_name())
                .addValue("age", user.getAge())
                .addValue("password", user.getPassword());


        return template.queryForObject(sql, parameterSource, Long.class);
    }

    public User getUserById(Long id){
        String sql = "SELECT * FROM userdb WHERE userdb.id=:id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id",id);
        return template.queryForObject(sql, parameterSource,(rs, rowNum)->{
            User user=new User();
            user.setId(rs.getLong("id"));
            user.setUser_name(rs.getString("user_name"));
            user.setAge(rs.getInt("age"));
            user.setPassword(rs.getString("password"));
            return user;
        });
    }

    public void editUser(User user){
        String sql ="UPDATE userdb SET user_name=:user_name, age=:age, password=:password WHERE id = :id";

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("user_name", user.getUser_name())
                .addValue("age", user.getAge())
                .addValue("password", user.getPassword());
        template.update(sql, parameterSource);

    }

    public void deleteUser(long id){
        String sql = "DELETE FROM userdb WHERE id=:id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id",id);
        template.update(sql,parameterSource);
    }

}
