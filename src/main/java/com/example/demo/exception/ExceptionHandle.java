package com.example.demo.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

//@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(InvalidResultSetAccessException.class)
    @ResponseBody
    public Result handleIRSAE(Exception e){
        Result result = new Result(ResultCode.FAILURE);
        String msg = e.getMessage();
        if (msg.length()!=0){
            result.setMessage(msg);
        }
        return result;
    }

    //JdbcTemplate.query返回结果为空时抛出的异常
    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseBody
    public Result handleERDAE(Exception e){
        Result result = new Result(ResultCode.FAILURE);
        String msg = e.getMessage();
        if (msg.length()!=0){
            result.setMessage(msg);
        }
        return result;
    }

    //得到消息需要转化成SQLException
    @ExceptionHandler(DataAccessException.class)
    @ResponseBody
    public Result handleDAE(DataAccessException e){
        SQLException sqlException = (SQLException) e.getCause();
        Result result = new Result(ResultCode.FAILURE);
        String msg = sqlException.getMessage();
        if (msg.length()!=0){
            result.setMessage(msg);
        }
        return result;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handleException(Exception e){
        Result result = new Result(ResultCode.FAILURE);
        String msg=e.getMessage();
        if (msg.length()!=0){
            result.setMessage(msg);
        }
        return result;
    }

}
