package com.mingyu.service.impl;

import com.mingyu.dao.AccountDao;
import com.mingyu.service.AccountService;

public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    @Override
    public String one() {
        System.out.println("AccountServiceImpl.one()方法执行");
        return accountDao.one();
    }
}
