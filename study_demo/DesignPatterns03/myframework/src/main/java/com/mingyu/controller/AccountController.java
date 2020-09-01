package com.mingyu.controller;

import com.mingyu.domain.User;
import com.mingyu.framework.util.RequestMapping;
import com.mingyu.service.AccountService;

/**
 * 账户控制器
 *
 * @date: 2020/8/20 18:41
 * @author: GingJingDM
 * @version: 1.0
 */
public class AccountController {

    private AccountService accountService;

    /**
     * 查询一条记录
     *
     * @return 跳转
     */
    @RequestMapping(value = "/account/one")
    public String one() {
        System.out.println("执行one");
        String result = null;
        return "/WEB-INF/pages/one.jsp";
    }

    /**
     * 查询一条记录
     *
     * @return User对象
     */
    @RequestMapping(value = "/account/info")
    public User info() {
        User user = new User();
        user.setName("王五");
        user.setAddress("深圳");
        return user;
    }
}
