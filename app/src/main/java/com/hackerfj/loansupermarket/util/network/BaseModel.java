package com.hackerfj.loansupermarket.util.network;

import com.blankj.utilcode.utils.StringUtils;

import java.io.Serializable;


public class BaseModel<T> implements Serializable {
    public String code;
    public String message;
    public T content;

    private static String SUCCEED_CODE = "10000";

    public boolean isSucceed() {
        return StringUtils.equals(code, "10000") ? true : false;
    }
    @Override
    public String toString() {
        return "BaseModel{" +
                "code='" + code + '\'' +
                ",message='" + message + '\'' +
                ",content=" + content +
                '}';
    }
}
