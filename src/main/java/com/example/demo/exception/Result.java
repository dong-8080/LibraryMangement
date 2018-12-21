package com.example.demo.exception;


public class Result<T> {

    private int code;
    private String message;
    private T body;

    public Result(ResultCode resultCode, T body) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.body = body;
    }


    public Result(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
