package com.example.demo.dao;

import com.example.demo.entity.UserEntity;
import com.example.demo.exception.DescribeException;
import com.example.demo.exception.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //得到用户信息
    public UserEntity getUser(String account){
        String sql = "SELECT * FROM Usr WHERE account = ?";
        try{
            return jdbcTemplate.queryForObject(sql,new Object[]{account},new UserEntityRowMapper());
        }catch (EmptyResultDataAccessException e){
            throw new DescribeException(ResultCode.USER_NOT_EXIST);
        }
    }

    //修改用户的密码
    public int alertPassword(String account,String password){
        String sql = "UPDATE Usr SET password=? WHERE account=?";
        return jdbcTemplate.update(sql,new Object[]{password,account});
    }

    //添加用户
    public int insertUser(String account,String password,String name){
        String sql = "insert into Usr(account,password,name) values(?,?,?)";
        try{
            return jdbcTemplate.update(sql,new Object[]{account,password,name});
        }catch (Exception e){
            throw new DescribeException(ResultCode.USER_ALREADY_EXISTS);
        }
    }

    private class UserEntityRowMapper implements RowMapper<UserEntity> {
        @Override
        public UserEntity mapRow(ResultSet resultSet, int i) throws SQLException {
            String account = resultSet.getString("account");
            String password = resultSet.getString("password");
            String name = resultSet.getString("name");
            int limit = resultSet.getInt("limit");
            int amount = resultSet.getInt("amount");

            UserEntity user = new UserEntity(account,password,name,limit,amount);
            return user;
        }
    }
}
