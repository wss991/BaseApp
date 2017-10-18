package com.td.baseapp.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by my on 2017/10/17.
 */

@Entity
public class TestBean {
    private int restId;
    private String name;
    @Generated(hash = 468184208)
    public TestBean(int restId, String name) {
        this.restId = restId;
        this.name = name;
    }
    @Generated(hash = 2087637710)
    public TestBean() {
    }
    public int getRestId() {
        return this.restId;
    }
    public void setRestId(int restId) {
        this.restId = restId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
}
