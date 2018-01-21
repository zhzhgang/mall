package com.zhzhgang.mall.solrj;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

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
}
