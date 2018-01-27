package com.zhzhgang.mall.search.service.impl;

import com.zhzhgang.mall.common.pojo.SearchResult;
import com.zhzhgang.mall.search.dao.SearchDao;
import com.zhzhgang.mall.search.service.SearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 搜索服务功能实现
 *
 * @author zhzhgang
 * @create 2018-01-27 17:27
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchDao searchDao;

    @Override
    public SearchResult search(String query, int page, int rows) {
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery(query);

        if (page < 1) {
            page = 1;
        }
        solrQuery.setStart((page - 1) * rows);

        if (rows < 1) {
            rows = 10;
        }
        solrQuery.setRows(rows);

        // 设置默认搜索域
        solrQuery.set("df", "item_title");

        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("item_titile");
        solrQuery.setHighlightSimplePre("<font color='red'>");
        solrQuery.setHighlightSimplePost("<font/>");

        SearchResult searchResult = new SearchResult();
        try {
            searchResult = searchDao.search(solrQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }

        long recordCount = searchResult.getRecordCount();
        long pageNum = recordCount / rows;
        if (recordCount % rows != 0) {
            pageNum += 1;
        }

        searchResult.setTotalPages(pageNum);

        return searchResult;
    }
}
