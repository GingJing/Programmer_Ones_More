package com.mingyu.shop.service.impl;

import com.mingyu.shop.dao.ItemDao;
import com.mingyu.shop.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    /***
     * 修改库存
     */
    @Override
    public int modify(Integer count, String id){
        return itemDao.modify(count,id);
    }

}
