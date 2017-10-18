package com.td.baseapp.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by my on 2017/9/30.
 */

@Entity
public class UserBean {

    @Id
    public String userId;
    public String userName;

    @Generated(hash = 288358919)
    public UserBean(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    @Generated(hash = 1203313951)
    public UserBean() {
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



    @Override
    public String toString() {
        return "UserBean{" +
                "id=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
