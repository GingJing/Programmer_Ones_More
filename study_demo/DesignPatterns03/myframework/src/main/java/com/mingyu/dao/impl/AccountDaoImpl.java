package com.mingyu.dao.impl;

import com.mingyu.dao.AccountDao;

public class AccountDaoImpl implements AccountDao {

    /**
     * 查询
     */
    @Override
    public String one() {
        System.out.println("Dao查询！");
        return "ok";
    }
}