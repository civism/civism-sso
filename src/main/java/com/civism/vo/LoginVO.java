package com.civism.vo;

/**
 * @author star
 * @date 2018/8/17 下午4:35
 */
public class LoginVO {
    private Integer type;
    private String nick;
    private String password;
    private String realmName;

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRealmName() {
        return realmName;
    }

    public void setRealmName(String realmName) {
        this.realmName = realmName;
    }
}
