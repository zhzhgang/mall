package com.zhzhgang.mall.search.controller;

import com.zhzhgang.mall.common.pojo.SearchResult;
import com.zhzhgang.mall.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;

/**
 * 商品搜索服务 Controller
 *
 * @author zhzhgang
 * @create 2018-01-27 18:03
 */
@Controller
public class SearchController {

    @Value("${SEARCH_RESULT_ROWS}")
    private Integer SEARCH_RESULT_ROWS;

    @Autowired
    private SearchService searchService;

    @RequestMapping("/search")
    public String search(@RequestParam("q") String queryString,
                         @RequestParam(defaultValue = "1") Integer page,
                         Model model) {

        // int i = 1 / 0;
        // 解决 get 乱码问题，把查询条件进行转码
        try {
            queryString = new String(queryString.getBytes("iso8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        SearchResult result = searchService.search(queryString, page, SEARCH_RESULT_ROWS);

        model.addAttribute("query", queryString);
        model.addAttribute("totalPages", result.getTotalPages());
        model.addAttribute("itemList", result.getItemList());
        model.addAttribute("page", page);

        // 返回逻辑视图
        return "search";
    }
}
