package com.mingyu.shop.dao;


import com.mingyu.shop.domain.Item;

public interface ItemDao {
    /***
     * 修改商品库存
     * @param count
     * @param id
     * @return
     */
    int modify(Integer count,String id);

    /***
     * 根据ID查找商品
     * @param id
     * @return
     */
    Item findById(String id);
}
