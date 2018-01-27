package com.zhzhgang.mall.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhzhgang
 * @create 2018-01-23 21:59
 */
public class SearchResult implements Serializable {

    private long totalPages;

    private long recordCount;

    private List<SearchItem> itemList;

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public List<SearchItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<SearchItem> itemList) {
        this.itemList = itemList;
    }

    public long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(long recordCount) {
        this.recordCount = recordCount;
    }
}
