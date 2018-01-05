package com.zhzhgang.mall.pagehelper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhzhgang.mall.mapper.MallItemMapper;
import com.zhzhgang.mall.pojo.MallItem;
import com.zhzhgang.mall.pojo.MallItemExample;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author zhangzhonggang
 * @create 2018-01-05 15:27
 */
public class TestPageHelper {

    @Test
    public void testPageHelper() throws Exception {
        PageHelper.startPage(1, 10);

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
        MallItemMapper mallItemMapper = applicationContext.getBean(MallItemMapper.class);

        MallItemExample example = new MallItemExample();
        List<MallItem> mallItems = mallItemMapper.selectByExample(example);

        PageInfo<MallItem> pageInfo = new PageInfo<MallItem>(mallItems);
        System.out.println("total = " + pageInfo.getTotal());
        System.out.println("pages = " + pageInfo.getPages());
        System.out.println("size = " + mallItems.size());
    }
}
