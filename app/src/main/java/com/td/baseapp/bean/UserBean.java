package com.td.baseapp.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by my on 2017/9/30.
 */

@Entity
public class UserBean {
    @Id(autoincrement = true)
    public Long id;
    public String userName;

    @Generated(hash = 1260553639)
    public UserBean(Long id, String userName) {
        this.id = id;
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

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
