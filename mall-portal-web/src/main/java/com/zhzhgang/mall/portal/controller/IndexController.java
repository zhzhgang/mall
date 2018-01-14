package com.zhzhgang.mall.portal.controller;

import com.zhzhgang.mall.common.utils.JsonUtils;
import com.zhzhgang.mall.content.service.ContentService;
import com.zhzhgang.mall.pojo.MallContent;
import com.zhzhgang.mall.portal.pojo.AD1Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * 展示首页 Controller
 *
 * @author zhzhgang
 * @create 2018-01-09 22:34
 */
@Controller
public class IndexController {

    @Value("${AD1_CATEGORY_ID}")
    private Long AD1_CATEGORY_ID;

    @Value("${AD1_WIDTH}")
    private Integer AD1_WIDTH;

    @Value("${AD1_WIDTH_B}")
    private Integer AD1_WIDTH_B;

    @Value("${AD1_HEIGHT}")
    private Integer AD1_HEIGHT;

    @Value("${AD1_HEIGHT_B}")
    private Integer AD1_HEIGHT_B;

    @Autowired
    private ContentService contentService;

    @RequestMapping("/index")
    public String showIndex(Model model) {
        List<MallContent> mallContentList = contentService.getContentByCid(AD1_CATEGORY_ID);
        List<AD1Node> ad1NodeList = new ArrayList<>();
        for (MallContent mallContent : mallContentList) {
            AD1Node ad1Node = new AD1Node();
            ad1Node.setAlt(mallContent.getTitle());
            ad1Node.setHeight(AD1_HEIGHT);
            ad1Node.setHeightB(AD1_HEIGHT_B);
            ad1Node.setWidth(AD1_WIDTH);
            ad1Node.setWidthB(AD1_WIDTH_B);
            ad1Node.setSrc(mallContent.getPic());
            ad1Node.setSrcB(mallContent.getPic2());
            ad1Node.setHref(mallContent.getUrl());

            ad1NodeList.add(ad1Node);
        }
        String ad1Json = JsonUtils.objectToJson(ad1NodeList);
        model.addAttribute("ad1", ad1Json);
        return "index";
    }
}
