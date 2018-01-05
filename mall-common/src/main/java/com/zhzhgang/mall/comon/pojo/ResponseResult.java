package com.zhzhgang.mall.comon.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhangzhonggang
 * @create 2018-01-05 15:08
 */
public class ResponseResult implements Serializable {

    private long total;

    private List rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
