package com.zhzhgang.mall.cart.controller;

import com.zhzhgang.mall.common.utils.CookieUtils;
import com.zhzhgang.mall.common.utils.JsonUtils;
import com.zhzhgang.mall.pojo.MallItem;
import com.zhzhgang.mall.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车管理 Controller
 * @author zhzhgang
 * @create 2018-03-17 22:52
 */
@Controller
public class CartController {

    @Value("${CART_KEY}")
    private String cartKey;

    @Value("${CART_EXPIRE}")
    private Integer cartExpire;

    @Autowired
    private ItemService itemService;

    @RequestMapping("/cart/add/{itemId}")
    public String addItemCart(@PathVariable Long itemId,
                              @RequestParam(defaultValue = "1") Integer num,
                              HttpServletRequest request,
                              HttpServletResponse response) {

        // 取购物车商品列表
        List<MallItem> cartItemList = getCartItemList(request);

        // 判断商品在购物车中是否存在
        boolean flag = false;
        for (MallItem item : cartItemList) {
            if (item.getId() == itemId.longValue()) {
                // 如存在，数量相加
                item.setNum(item.getNum() + num);
                flag = true;
                break;
            }
        }

        // 如不存在，添加一个新商品
        if (!flag) {
            // 调用服务取商品信息
            MallItem item = itemService.getItemById(itemId);
            // 设置购买的商品数量
            item.setNum(num);

            String image = item.getImage();
            if (StringUtils.isNotBlank(image)) {
                String[] images = image.split(",");
                item.setImage(images[0]);
            }
            cartItemList.add(item);
        }
        // 把购物车列表写入 cookie
        CookieUtils.setCookie(request, response, cartKey, JsonUtils.objectToJson(cartItemList), cartExpire, true);

        return "cartSuccess";
    }


    private List<MallItem> getCartItemList(HttpServletRequest request) {
        // 从 cookie 中取购物车商品列表
        String cookieValue = CookieUtils.getCookieValue(request, cartKey, true);
        if (StringUtils.isBlank(cookieValue)) {
            return new ArrayList<>();
        }
        List<MallItem> list = JsonUtils.jsonToList(cookieValue, MallItem.class);
        return list;
    }
}
