package com.hackerfj.loansupermarket.model.entity.res;

import java.util.List;

/**
 *
 * @author fujie
 * @date 2018/6/7
 */

public class GetHomebannerRes {

//            "         id": 1,
//                    "type": 1,
//                    "class": "test",
//                    "money": "68",
//                    "time": "1539588985"

    private String id;
    private String type;
    private String aclass;
    private String money;
    private String time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAclass() {
        return aclass;
    }

    public void setAclass(String aclass) {
        this.aclass = aclass;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
