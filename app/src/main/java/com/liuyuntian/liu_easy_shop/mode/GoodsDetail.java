package com.liuyuntian.liu_easy_shop.mode;

import java.util.List;



//"uuid": "5606FF8EF60146A48F1FCDC34144907D",     //商品表中主键
//        "name": "货物",                                   //商品名称
//        "type": "other",                                //商品类型
//        "price": "66",                                  //商品价格
//        "description": ".......",                       //商品描述
//        "master": "android",                            //商品发布者
//        "pages": [

//商品展示详情类
public class GoodsDetail {

    /*名称*/
    private String name;
    /*类型*/
    private String type;
    /*价格*/
    private String price;
    /*商品描述*/
    private String description;
    /*发布者*/
    private String master;
    /*商品图片uri*/
    private List<ImageUri> pages;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getMaster() {
        return master;
    }

    public List<ImageUri> getPages() {
        return pages;
    }

    public class ImageUri{
        private String uri;

        public String getUri() {
            return uri;
        }
    }

}
