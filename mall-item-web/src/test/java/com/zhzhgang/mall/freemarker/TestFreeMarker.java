package com.zhzhgang.mall.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.*;

/**
 * @author zhzhgang
 * @create 2018-03-10 10:14
 */
public class TestFreeMarker {

    @Test
    public void testFreemarker() throws Exception {
        // 1.创建一个模板文件

        // 2.创建一个 Configuration 对象
        Configuration configuration = new Configuration(Configuration.getVersion());

        // 3.设置模板所在路径
        configuration.setDirectoryForTemplateLoading(new File("/Users/zhangzhonggang/GitRepository/mall/mall-item-web/src/main/webapp/WEB-INF/ftl"));

        // 4.设置模板字符集，一般 UTF-8
        configuration.setDefaultEncoding("UTF-8");

        // 5.使用 Configuration 对象加载模板文件，需要指定模板文件的文件名
        // Template template = configuration.getTemplate("hello.ftl");
        Template template = configuration.getTemplate("student.ftl");

        // 6.创建一个数据集，pojo 或者 map(推荐)
        Map data = new HashMap();
        data.put("hello", "hello freemarker");
        Student student = new Student(1, "jack", 20, "男");
        data.put("student", student);
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(2, "Jackson", 20, "男"));
        studentList.add(new Student(3, "Lucy", 20, "女"));
        studentList.add(new Student(4, "Mary", 20, "女"));

        data.put("studentList", studentList);

        data.put("date", new Date());

        // 7.创建一个 Writer 对象，指定输出文件的路径及文件名
        Writer out = new FileWriter("/Users/zhangzhonggang/Desktop/temp/out/student.html");

        // 8.使用模板对象的 process 方法输出文件
        template.process(data, out);

        // 9.关闭流
        out.close();
    }
}
