package com.example.demo.dao;

import com.example.demo.entity.BookEntity;
import com.example.demo.entity.BorrowEntity;
import com.example.demo.exception.DescribeException;
import com.example.demo.exception.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


@Repository
public class BookDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //续借书籍 若已续借，则抛出异常
    public int renewBook(String account, String bookId) {
        try {
            boolean renew = jdbcTemplate.queryForObject("select renew from Borrow where account=? and bookId=?", new Object[]{account, bookId}, Boolean.class);
            if (!renew) {
                String sql2 = "update Borrow set renew = 1,retDate = getDate()+30 where account=? and bookID=?";
                return jdbcTemplate.update(sql2, new Object[]{account, bookId});
            } else {
                throw new DescribeException(ResultCode.BOOK_IS_RENEWED);
            }
        } catch (EmptyResultDataAccessException e) {
            throw new DescribeException(ResultCode.PARAMETER_IS_INCORRECT);
        }
    }


//    //还书操作 删除Borrow表中的数据并将数据插入到Insert表中 将ISBN转化为bookID
//    @Transactional(propagation = Propagation.NESTED)
//    public int returnBook(String account, String bookId) {
//        jdbcTemplate.update("delete Borrow where account=? and bookId=?", new Object[]{account, bookId});
//        return jdbcTemplate.update("insert into [Return](account,bookId) values(?,?)", new Object[]{account, bookId});
//    }

    //还书操作 调用存储过程
    public int returnBook(String account, String bookID) {
        return (Integer) jdbcTemplate.execute(
                con -> {
                    String storedProc = "{call returnBook(?,?,?)}";// 调用的sql
                    CallableStatement cs = con.prepareCall(storedProc);
                    cs.setString(1, account);//设置输入参数的值
                    cs.setString(2, bookID);
                    cs.registerOutParameter(3, Types.INTEGER);// 注册输出参数的类型
                    return cs;
                }, (CallableStatementCallback) cs -> {
                    cs.execute();
                    return cs.getInt(3);// 获取输出参数的值
                });
    }

    //借书  调用存储过程
    public int borrowBook(String account, String isbn) {
        return (Integer) jdbcTemplate.execute(
                con -> {
                    String storedProc = "{call borrowBook(?,?,?)}";// 调用的sql
                    CallableStatement cs = con.prepareCall(storedProc);
                    cs.setString(1, account);//设置输入参数的值
                    cs.setString(2, isbn);
                    cs.registerOutParameter(3, Types.INTEGER);// 注册输出参数的类型
                    return cs;
                }, (CallableStatementCallback) cs -> {
                    cs.execute();
                    return cs.getInt(3);// 获取输出参数的值
                });
    }


    //查询书籍 根据ISBN
    public List findBookByISBN(String ISBN) {
        String sql = "select * from Book where ISBN = " + ISBN.trim();
        return jdbcTemplate.queryForList(sql);
    }

    //查询书籍 根据书名
    public List findBookByTitle(String title) {
        String sql = "select * from Book where title like ?";
        return jdbcTemplate.queryForList(sql, new Object[]{"%" + title + "%"});
    }

    //查询书籍 根据作者
    public List findBookByAuthor(String author) {
        String sql = "select * from Book where author like ?";
        return jdbcTemplate.queryForList(sql, new Object[]{"%" + author + "%"});
    }

    //查询书籍 根据出版社
    public List findBookByPress(String press) {
        String sql = "select * from Book where press like ?";
        return jdbcTemplate.queryForList(sql, new Object[]{"%" + press + "%"});
    }


    public List borrowList(String account){
        String sql = "select * from BorrowView where account=? ";
        return jdbcTemplate.queryForList(sql,new Object[]{account});
    }

    private class BookRowMapper implements RowMapper<BookEntity> {
        @Override
        public BookEntity mapRow(ResultSet resultSet, int i) throws SQLException {
            String ISBN = resultSet.getString("ISBN");
            String classify = resultSet.getString("classify");
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            String press = resultSet.getString("press");
            String preDate = resultSet.getString("preDate");
            int duplicate = resultSet.getInt("duplicate");
            int allowance = resultSet.getInt("allowance");

            BookEntity entity = new BookEntity();
            entity.setISBN(ISBN);
            entity.setClassify(classify);
            entity.setTitle(title);
            entity.setAuthor(author);
            entity.setPress(press);
            entity.setPreDate(preDate);
            entity.setDuplicate(duplicate);
            entity.setAllowance(allowance);
            return entity;
        }
    }

    private class BorrowRowMapper<T> implements RowMapper<BorrowEntity> {
        @Override
        public BorrowEntity mapRow(ResultSet resultSet, int i) throws SQLException {
            String bookId = resultSet.getString("bookId");
            String ISBN = resultSet.getString("ISBN");
            String account = resultSet.getString("account");
            long borrowDate = resultSet.getDate("borDate").getTime();
            long returnDate = resultSet.getDate("retDate").getTime();
            boolean renew = resultSet.getBoolean("renew");

            BorrowEntity entity = new BorrowEntity();
            entity.setBookId(bookId);
            entity.setISBN(ISBN);
            entity.setAccount(account);

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            entity.setBorrowDate(format.format(borrowDate));
            entity.setReturnDate(format.format(returnDate));

            entity.setRenew(renew);
            return entity;
        }
    }
}
