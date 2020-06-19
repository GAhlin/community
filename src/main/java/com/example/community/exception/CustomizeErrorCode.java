package com.example.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND(2001, "你找的问题不在了，要不换个试试？"),
    TARGET_PARAM_NOT_FOUND(2002, "未选中任何问题或评论进行回复！"),
    NO_LOGIN(2003, "当前操作需要登录，请登陆后重试！"),
    SYSTEM_ERROR(2004, "服务冒烟了，要不然你稍后再试试！！！"),
    TYPE_PARAM_WRONG(2005, "评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006, "回复的评论不存在，要不换个试试？"),
    CONTENT_IS_EMPTY(2007, "输入内容不能为空！"),
    READ_NOTIFICATION_FAIL(2008, "兄弟你这是读别人的信息呢？"),
    NOTIFICATION_NOT_FOUND(2009, "消息不存在！"),
    ACCOUNT_FAIL(2010, "账号异常，请重新登陆！"),
    UPLOAD_FILE_ERROR(2011, "上传出错了，请检查你的文件格式！"),
    IS_NOT_LEGAL(2012, "非法入侵，请停止你的行为"),
    SEND_EMAIL_FAIL(2013, "邮件发送失败"),
    EMAIL_ALREADY_EXISTS(2014, "邮箱已注册"),
    PWD_NOT_EQUAL(2015, "两次输入的密码不一致"),
    EMAIL_NOT_EXISTS(2016, "邮箱未注册"),
    INVALID_OPERATION(2017, "兄弟，是不是走错房间了？"),
    ;

    private Integer code;
    private String message;

    CustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

}
