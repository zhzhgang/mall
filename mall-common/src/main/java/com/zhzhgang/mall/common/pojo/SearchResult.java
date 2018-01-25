package com.zhzhgang.mall.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhzhgang
 * @create 2018-01-23 21:59
 */
public class SearchResult implements Serializable {

    private int totalPages;

    private List<SearchItem> itemList;

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<SearchItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<SearchItem> itemList) {
        this.itemList = itemList;
    }
}
