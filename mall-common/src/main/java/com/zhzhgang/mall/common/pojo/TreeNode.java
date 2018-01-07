package com.zhzhgang.mall.common.pojo;

import java.io.Serializable;

/**
 * @author zhzhgang
 * @create 2018-01-07 18:11
 */
public class TreeNode implements Serializable {

    private long id;
    private String text;
    private String state;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
