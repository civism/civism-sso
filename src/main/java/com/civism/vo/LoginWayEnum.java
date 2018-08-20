package com.civism.vo;

/**
 * @author star
 * @date 2018/8/17 下午4:35
 */
public enum LoginWayEnum {

    ACCOUNT_LOGIN(1, " 账号密码登陆");

    private Integer way;

    private String desc;


    LoginWayEnum(Integer way, String desc) {
        this.way = way;
        this.desc = desc;
    }

    public Integer getWay() {
        return way;
    }

    public void setWay(Integer way) {
        this.way = way;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
