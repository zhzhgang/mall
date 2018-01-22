package com.zhzhgang.mall.search.service.impl;

import com.zhzhgang.mall.common.pojo.MallResult;
import com.zhzhgang.mall.common.pojo.SearchItem;
import com.zhzhgang.mall.search.mapper.SearchItemMapper;
import com.zhzhgang.mall.search.service.SearchItemService;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhzhgang
 * @create 2018-01-22 22:14
 */
@Service
public class SearchItemServiceImpl implements SearchItemService {

    @Autowired
    private SearchItemMapper searchItemMapper;

    @Autowired
    SolrServer solrServer;

    /**
     * 导入商品数据到数据库
     *
     * @return
     */
    @Override
    public MallResult importItemsToIndex() {

        try {
            // 1. 查询所有商品数据
            List<SearchItem> list = searchItemMapper.getItemList();

            // 2. 遍历商品数据添加到索引库
            for (SearchItem item : list) {
                SolrInputDocument document = new SolrInputDocument();
                document.addField("id", item.getId());
                document.addField("item_title", item.getTitle());
                document.addField("item_sell_point", item.getSell_point());
                document.addField("item_price", item.getPrice());
                document.addField("item_image", item.getImage());
                document.addField("item_category_name", item.getCategory_name());
                document.addField("item_desc", item.getItem_desc());

                solrServer.add(document);
            }
            // 3. 提交
            solrServer.commit();

        } catch (Exception e) {
            e.printStackTrace();
            return MallResult.build(500, "数据导入失败");
        }

        return MallResult.ok();
    }
}
