package com.zhzhgang.mall.item.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * 网页静态处理 Controller
 * @author zhzhgang
 * @create 2018-03-10 19:55
 */
@Controller
public class HtmlGenController {

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @RequestMapping("/genhtml")
    @ResponseBody
    public String genHtml() throws Exception {
        // 生成静态页面
        Configuration configuration = freeMarkerConfigurer.getConfiguration();
        Template template = configuration.getTemplate("hello.ftl");
        Map data = new HashMap();
        data.put("hello", "hello freemarker");
        Writer out = new FileWriter(new File("/Users/zhangzhonggang/Desktop/temp/out/test.html"));
        template.process(data, out);
        out.close();

        return "OK";
    }


}
