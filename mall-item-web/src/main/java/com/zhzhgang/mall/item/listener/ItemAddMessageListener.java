package com.zhzhgang.mall.item.listener;

import com.zhzhgang.mall.item.pojo.Item;
import com.zhzhgang.mall.pojo.MallItem;
import com.zhzhgang.mall.pojo.MallItemDesc;
import com.zhzhgang.mall.service.ItemService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhzhgang
 * @create 2018-03-10 20:28
 */
public class ItemAddMessageListener implements MessageListener {

    @Autowired
    private ItemService itemService;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Value("${HTML_OUT_PATH}")
    private String htmlOutPath;

    @Override
    public void onMessage(Message message) {
        try {
            // 从消息中取商品 ID
            TextMessage textMessage = (TextMessage) message;
            Long itemId = Long.parseLong(textMessage.getText());

            // 等待添加商品事务提交
            Thread.sleep(1000);

            // 根据商品 ID 查询商品信息及商品描述
            MallItem mallItem = itemService.getItemById(itemId);
            Item item = new Item(mallItem);
            MallItemDesc itemDesc = itemService.getItemDescById(itemId);

            // 使用 freemarker 生成静态页面
            Configuration configuration = freeMarkerConfigurer.getConfiguration();
            // 1.创建模板
            // 2.加载模板对象
            Template template = configuration.getTemplate("item.ftl");
            // 3.准备模板需要的数据
            Map data = new HashMap();
            data.put("item", item);
            data.put("itemDesc", itemDesc);

            // 4.指定输出的目录及文件名
            Writer out = new FileWriter(new File(htmlOutPath + itemId + ".html"));

            // 5.生成静态页面
            template.process(data, out);

            // 关闭流
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
