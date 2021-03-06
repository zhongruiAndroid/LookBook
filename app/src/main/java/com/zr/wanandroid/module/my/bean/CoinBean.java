package com.zr.wanandroid.module.my.bean;

import com.github.developtools.N;

import java.io.Serializable;

public class CoinBean implements Serializable {
    /**
     * coinCount : 10
     * level : 1
     * rank : 55406
     * userId : 81275
     * username : 1**21060271
     */

    private String coinCount;
    private String level;
    private String rank;
    private String userId;
    private String username;

    public String getCoinCount() {
        return N.trimToEmptyNull(coinCount)?"--":coinCount;
    }

    public void setCoinCount(String coinCount) {
        this.coinCount = coinCount;
    }

    public String getLevel() {
        return level==null?"--":level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRank() {
        return rank==null?"--":rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
