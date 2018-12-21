package com.example.demo.exception;

public enum ResultCode {
    SUCCESS(200,"请求成功"),
    FAILURE(400,"请求失败，未知错误"),
    LOGIN_ERROR(410,"用户密码错误"),
    USER_NOT_EXIST(411,"用户不存在"),
    USER_ALREADY_EXISTS(412,"该用户已存在"),
    USER_NOT_LOGIN(413,"未登录，请先登陆"),
    USER_CAN_NOT_BORROW(414,"该用户无法借阅"),
    BOOK_NOT_FOUNT(420,"未找到匹配的书籍"),
    BOOK_IS_RENEWED(421,"已续借，无法再次续借"),
    BOOK_HAS_EXIST(422,"该书籍已存在"),
    ERROR_IN_BORROW(423,"借书失败，请稍后再试"),
    ERROR_IN_RETURN(424,"还书失败，请稍后再试"),
    PARAMETER_IS_INCORRECT(430,"请求参数不正确"),

    ;

    private int code;
    private String message;

    private ResultCode(int code,String message){
        this.code = code;
        this.message = message;
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

}
