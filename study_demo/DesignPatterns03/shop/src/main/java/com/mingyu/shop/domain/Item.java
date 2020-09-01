package com.mingyu.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Item implements Serializable {

    /** 商品id */
    private String id;

    /** 商品名称 */
    private String name;

    /** 商品描述 */
    private String description;

    /** 商品图片 */
    private String images;

    /** 商品视频路径 */
    private String video;

    /** 数量 */
    private Integer count;

    /** 价格 */
    private Integer price;
}
