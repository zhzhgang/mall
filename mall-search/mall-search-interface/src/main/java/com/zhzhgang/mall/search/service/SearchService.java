package com.zhzhgang.mall.search.service;

import com.zhzhgang.mall.common.pojo.SearchResult;

public interface SearchService {

    SearchResult search(String query, int page, int rows);

}
