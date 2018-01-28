package com.zhzhgang.mall.solrj;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.io.IOException;

/**
 * @author zhzhgang
 * @create 2018-01-28 15:44
 */
public class TestSolrCloud {

    @Test
    public void testSolrCloudAddDocument() throws IOException, SolrServerException {
        // 创建 CloudSolrServer 对象，构造方法中需要 zookeeper 地址列表
        CloudSolrServer cloudSolrServer = new CloudSolrServer("192.168.56.101:2182,192.168.56.101:2183,192.168.56.101:2184");

        // 设置默认的 Collection
        cloudSolrServer.setDefaultCollection("collection2");

        // 创建 Document 对象
        SolrInputDocument document = new SolrInputDocument();

        // 向文档中添加域
        document.addField("id", "test001");
        document.addField("item_title", "小米手机");
        document.addField("item_price", 100);

        // 把文档写入索引库
        cloudSolrServer.add(document);

        // 提交
        cloudSolrServer.commit();
    }
}
