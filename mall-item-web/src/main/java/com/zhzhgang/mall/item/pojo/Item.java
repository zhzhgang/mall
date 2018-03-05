package com.zhzhgang.mall.item.pojo;

import com.zhzhgang.mall.pojo.MallItem;

/**
 * @author zhzhgang
 * @create 2018-03-05 21:46
 */
public class Item extends MallItem {

    public Item(MallItem mallItem) {
        // 初始化属性
        this.setId(mallItem.getId());
        this.setTitle(mallItem.getTitle());
        this.setSellPoint(mallItem.getSellPoint());
        this.setPrice(mallItem.getPrice());
        this.setNum(mallItem.getNum());
        this.setBarcode(mallItem.getBarcode());
        this.setImage(mallItem.getImage());
        this.setCid(mallItem.getCid());
        this.setStatus(mallItem.getStatus());
        this.setCreated(mallItem.getCreated());
        this.setUpdated(mallItem.getUpdated());
    }

    public String[] getImages() {
        if (this.getImage() != null && !"".equals(this.getImage())) {
            String image = this.getImage();
            String[] strings = image.split(",");
            return strings;
        }
        return null;
    }
}
