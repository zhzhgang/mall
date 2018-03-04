package com.zhzhgang.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhzhgang.mall.common.pojo.MallResult;
import com.zhzhgang.mall.common.pojo.PageResult;
import com.zhzhgang.mall.common.utils.IDUtils;
import com.zhzhgang.mall.mapper.MallItemDescMapper;
import com.zhzhgang.mall.mapper.MallItemMapper;
import com.zhzhgang.mall.pojo.MallItem;
import com.zhzhgang.mall.pojo.MallItemDesc;
import com.zhzhgang.mall.pojo.MallItemExample;
import com.zhzhgang.mall.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.*;
import java.util.Date;
import java.util.List;

/**
 * 商品管理 Service
 * @author zhangzhonggang
 * @create 2018-01-04 16:00
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    MallItemMapper mallItemMapper;

    @Autowired
    MallItemDescMapper mallItemDescMapper;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Resource(name = "itemAddTopic")
    private Destination destination;

    @Override
    public MallItem getItemById(long itemId) {
        return mallItemMapper.selectByPrimaryKey(itemId);
    }

    @Override
    public PageResult getItemList(int page, int rows) {
        PageHelper.startPage(page, rows);

        MallItemExample example = new MallItemExample();
        List<MallItem> list = mallItemMapper.selectByExample(example);

        PageInfo<MallItem> pageInfo = new PageInfo<MallItem>(list);

        PageResult pageResult = new PageResult();
        pageResult.setRows(list);
        pageResult.setTotal(pageInfo.getTotal());
        return pageResult;
    }


    /**
     * 添加商品
     *
     * @param mallItem : 商品对象
     * @param desc     : 商品描述
     * @return MallResult
     */
    @Override
    public MallResult addItem(final MallItem mallItem, String desc) {
        final long itemId = IDUtils.genItemId();
        mallItem.setId(itemId);
        // 商品状态：1-正常；2-下架；3-删除
        mallItem.setStatus((byte) 1);
        mallItem.setCreated(new Date());
        mallItem.setUpdated(new Date());
        mallItemMapper.insert(mallItem);

        MallItemDesc mallItemDesc = new MallItemDesc();
        mallItemDesc.setItemId(mallItem.getId());
        mallItemDesc.setItemDesc(desc);
        mallItemDesc.setCreated(new Date());
        mallItemDesc.setUpdated(new Date());
        mallItemDescMapper.insert(mallItemDesc);

        // 向 ActiveMQ 发送商品添加的消息
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                // 发送商品 ID
                TextMessage message = session.createTextMessage(itemId + "");
                return message;
            }
        });

        return MallResult.ok();
    }
}
