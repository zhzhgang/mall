package com.zhzhgang.mall.search.listener;

import com.zhzhgang.mall.common.pojo.SearchItem;
import com.zhzhgang.mall.search.mapper.SearchItemMapper;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 监听商品添加消息，同步索引库
 *
 * @author zhzhgang
 * @create 2018-03-04 17:10
 */
public class ItemAddMessageListener implements MessageListener {

    @Autowired
    private SearchItemMapper searchItemMapper;

    @Autowired
    private SolrServer solrServer;

    @Override
    public void onMessage(Message message) {

        try {
            // 根据商品 ID 查询商品信息
            TextMessage textMessage = (TextMessage) message;
            long itemId = Long.parseLong(textMessage.getText());

            // 从消息中取商品 ID
            // 等待商品添加事务提交
            Thread.sleep(1000);
            SearchItem item = searchItemMapper.getItemById(itemId);

            // 创建文档对象
            SolrInputDocument document = new SolrInputDocument();

            // 向文档对象中添加域
            document.addField("id", item.getId());
            document.addField("item_title", item.getTitle());
            document.addField("item_sell_point", item.getSell_point());
            document.addField("item_price", item.getPrice());
            document.addField("item_image", item.getImage());
            document.addField("item_category_name", item.getCategory_name());
            document.addField("item_desc", item.getItem_desc());
            // 把文档对象写入索引库
            solrServer.add(document);

            // 提交
            solrServer.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
