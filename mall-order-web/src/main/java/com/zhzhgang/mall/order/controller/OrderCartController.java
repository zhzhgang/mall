package com.zhzhgang.mall.order.controller;

import com.zhzhgang.mall.common.pojo.MallResult;
import com.zhzhgang.mall.common.utils.CookieUtils;
import com.zhzhgang.mall.common.utils.JsonUtils;
import com.zhzhgang.mall.order.pojo.OrderInfo;
import com.zhzhgang.mall.order.service.OrderService;
import com.zhzhgang.mall.pojo.MallItem;
import com.zhzhgang.mall.pojo.MallUser;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单确认页面处理 Controller
 * @author zhzhgang
 * @create 2018-03-18 22:23
 */
@Controller
public class OrderCartController {

    @Value("${CART_KEY}")
    private String cartKey;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/order/order-cart")
    public String showOrderCart(HttpServletRequest request) {
        // 用户必须是登录状态
        MallUser user = (MallUser) request.getAttribute("user");

        // 取用户 ID
        // 根据用户信息取收货地址列表，使用静态数据
        // 把收货地址列表传递给页面
        // 从 cookie 中取出购物车商品列表展示到页面
        List<MallItem> cartItemList = getCartItemList(request);
        request.setAttribute("cartList", cartItemList);

        // 返回逻辑视图
        return "order-cart";
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

    /**
     * 生成订单处理
     * @param orderInfo
     * @return
     */
    @RequestMapping(value = "/order/create", method = RequestMethod.POST)
    public String createOrder(OrderInfo orderInfo, Model model) {
        MallResult result = orderService.createOrder(orderInfo);
        model.addAttribute("orderId", result.getData().toString());
        model.addAttribute("payment", orderInfo.getPayment());
        DateTime dateTime = new DateTime();
        dateTime = dateTime.plusDays(3);
        model.addAttribute("date", dateTime.toString("yyyy-MM-dd"));
        return "success";
    }

}
