package com.example.demo.exception;

//用于抛出自定义的异常
public class DescribeException extends RuntimeException {

    private int code;

    public DescribeException(ResultCode resultCode) {
        this(resultCode.getCode(),resultCode.getMessage());
    }

    public DescribeException(int code,String message){
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
