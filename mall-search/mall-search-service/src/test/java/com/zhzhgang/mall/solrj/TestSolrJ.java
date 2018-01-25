package com.zhzhgang.mall.solrj;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @author zhzhgang
 * @create 2018-01-21 22:24
 */
public class TestSolrJ {

    @Test
    public void testAddDocument() throws Exception {
        // 创建一个 SolrServer 对象，使用 HttpSolrServer
        // 指定 solr 服务的 URL
        SolrServer solrServer = new HttpSolrServer("http://192.168.56.101:8080/solr/collection1");

        // 创建文档对象 SolrInputDocument
        SolrInputDocument document = new SolrInputDocument();

        // 向文档中添加域，必须要有 id 域，域的名称必须在 schema.xml 中定义
        document.addField("id", "test001");
        document.addField("item_title", "商品1");
        document.addField("item_price", 1000);

        // 把文档对象写入索引库
        solrServer.add(document);

        // 提交
        solrServer.commit();
    }

    @Test
    public void testDeleteDocumentById() throws Exception {
        SolrServer solrServer = new HttpSolrServer("http://192.168.56.101:8080/solr/collection1");
        solrServer.deleteById("test001");

        solrServer.commit();
    }

    @Test
    public void searchDocument() throws Exception {
        // 创建 SolrServer 对象
        SolrServer solrServer = new HttpSolrServer("http://192.168.56.101:8080/solr/collection1");

        // 创建 SolrQuery 对象
        SolrQuery solrQuery = new SolrQuery();

        // 设置查询等条件
        // solrQuery.setQuery("*:*");
        solrQuery.setQuery("手机");
        solrQuery.setStart(20);
        solrQuery.setRows(10);
        // 设置默认搜索域
        solrQuery.set("df", "item_keywords");
        // 设置高亮
        solrQuery.setHighlight(true);
        // 设置高亮的域
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlightSimplePre("<em>");
        solrQuery.setHighlightSimplePost("</em>");

        // 执行查询，得到 Response 对象
        QueryResponse response = solrServer.query(solrQuery);

        // 取查询结果
        SolrDocumentList solrDocumentList = response.getResults();

        // 取查询结果总记录数
        System.out.println(solrDocumentList.getNumFound());
        for (SolrDocument document : solrDocumentList) {
            System.out.println(document.get("id"));
            // 取高亮显示
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            List<String> list = highlighting.get(document.get("id")).get("item_title");
            String itemTitle = "";
            if (list != null && list.size() > 0) {
                itemTitle = list.get(0);
            } else {
                itemTitle = (String) document.get("item_title");
            }
            System.out.println("-----------zhzhgang-----------itemTitle值 = " + itemTitle + ", " + "当前类 = TestSolrJ.searchDocument()");
            System.out.println(document.get("item_title"));
            System.out.println(document.get("item_price"));
            System.out.println("-------------++++++---------------");
        }
    }
}
