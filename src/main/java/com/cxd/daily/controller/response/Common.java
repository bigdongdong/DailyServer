package com.cxd.daily.controller.response;

public class Common<T>{
    private int code ;
    private T content ;
    private String message ;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public Common<T> create(T content, String message){
        this.setCode(content == null ? -1 : 1);
        this.setContent(content);
        this.setMessage(message);
        return this;
    }
    public Common<T> create(int code ,T content, String message){
        this.setCode(code);
        this.setContent(content);
        this.setMessage(message);
        return this;
    }

    @Override
    public String toString() {
        return "Common{" +
                "code=" + code +
                ", content=" + content +
                ", message='" + message + '\'' +
                '}';
    }
}
