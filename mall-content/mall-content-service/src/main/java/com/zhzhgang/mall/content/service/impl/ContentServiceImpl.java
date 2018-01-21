package com.zhzhgang.mall.content.service.impl;

import com.zhzhgang.mall.common.pojo.MallResult;
import com.zhzhgang.mall.common.utils.JsonUtils;
import com.zhzhgang.mall.content.service.ContentService;
import com.zhzhgang.mall.jedis.JedisClient;
import com.zhzhgang.mall.mapper.MallContentMapper;
import com.zhzhgang.mall.pojo.MallContent;
import com.zhzhgang.mall.pojo.MallContentExample;
import com.zhzhgang.mall.pojo.MallContentExample.Criteria;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author zhzhgang
 * @create 2018-01-14 20:29
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private MallContentMapper mallContentMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${INDEX_CONTENT}")
    private String INDEX_CONTENT;

    /**
     * 添加内容
     *
     * @param content
     * @return
     */
    @Override
    public MallResult addContent(MallContent content) {
        content.setCreated(new Date());
        content.setUpdated(new Date());
        mallContentMapper.insert(content);

        // 同步缓存
        // 删除对应的缓存信息
        jedisClient.hdel(INDEX_CONTENT, content.getCategoryId().toString());

        return MallResult.ok();
    }

    /**
     * 根据内容分类 id 查询内容列表
     *
     * @param cid : 内容分类 id
     * @return
     */
    @Override
    public List<MallContent> getContentByCid(long cid) {

        // 先查询缓存
        try {
            String json = jedisClient.hget(INDEX_CONTENT, cid + "");
            if (StringUtils.isNotBlank(json)) {
                List<MallContent> list = JsonUtils.jsonToList(json, MallContent.class);
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 如果缓存未命中，查询数据库
        MallContentExample example = new MallContentExample();
        Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(cid);
        List<MallContent> list = mallContentMapper.selectByExample(example);

        // 将数据库查询结果添加到缓存
        try {
            jedisClient.hset(INDEX_CONTENT, cid + "", JsonUtils.objectToJson(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
