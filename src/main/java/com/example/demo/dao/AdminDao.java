package com.example.demo.dao;

import com.example.demo.entity.AdminEntity;
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
public class AdminDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //获取管理员用户
    public AdminEntity getUser(String account) {
        String sql = "select * from [Admin] where account = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{account}, new AdminRowMapper());
        } catch (EmptyResultDataAccessException e) {
            throw new DescribeException(ResultCode.USER_NOT_EXIST);
        }
    }

    //添加书籍
    public int addBook(String ISBN, String classify, String title, String author, String press, String preDate, int duplicate) {
        String sql = "insert into Book(ISBN,classify,title,author,press,preDate,duplicate) values(?,?,?,?,?,?,?)";

            return jdbcTemplate.update(sql, new Object[]{ISBN, classify, title, author, press, preDate, duplicate});

    }

    //删除书籍
    public int removeBook(String ISBN) {
        String sql = "delete from Book where ISBN=?";
        return jdbcTemplate.update(sql, new Object[]{ISBN});
    }


    private class AdminRowMapper implements RowMapper<AdminEntity> {
        @Override
        public AdminEntity mapRow(ResultSet resultSet, int i) throws SQLException {
            String account = resultSet.getString("account");
            String password = resultSet.getString("password");
            AdminEntity entity = new AdminEntity();
            entity.setAccount(account);
            entity.setPassword(password);
            return entity;
        }
    }
}
