package com.zhzhgang.mall.search.dao;

import com.zhzhgang.mall.common.pojo.SearchItem;
import com.zhzhgang.mall.common.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 查询索引库商品 dao
 * @author zhzhgang
 * @create 2018-01-27 16:50
 */
@Repository
public class SearchDao {

    @Autowired
    private SolrServer solrServer;

    public SearchResult search(SolrQuery solrQuery) throws Exception {
        // 根据 solrQuery 对象进行查询
        QueryResponse response = solrServer.query(solrQuery);

        // 取查询结果
        SolrDocumentList solrDocumentList = response.getResults();

        // 取查询结果总记录数
        long numFound = solrDocumentList.getNumFound();
        SearchResult searchResult = new SearchResult();
        searchResult.setRecordCount(numFound);

        // 把查询结果封装到 SearchItem 对象中
        List<SearchItem> searchItemList = new ArrayList<>();
        for (SolrDocument document : solrDocumentList) {
            SearchItem item = new SearchItem();
            item.setId((String) document.get("id"));
            item.setCategory_name((String) document.get("item_category_name"));
            item.setImage((String) document.get("item_image"));
            item.setPrice((Long) document.get("item_price"));
            item.setSell_point((String) document.get("item_sell_point"));

            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            List<String> list = highlighting.get(document.get("id")).get("item_title");
            String title = "";
            if (list != null && list.size() > 0) {
                title = list.get(0);
            } else {
                title = (String) document.get("item_title");
            }
            item.setTitle(title);

            searchItemList.add(item);
        }
        // 把结果添加到 SearchResult 中
        searchResult.setItemList(searchItemList);
        // 返回
        return searchResult;
    }
}
