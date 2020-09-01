package com.mingyu.tenet.three.news;

/*****
 * @Author: http://www.itheima.com
 * @Description: com.itheima.tenet.three.old.CommonCustomer
 ****/
public class CommonCustomer extends Customer{

    @Override
    public String sefInfo() {
        return "给普通客户发消息，客户名字：" + super.getName() + ",客户手机号："+super.getPhone();
    }
}
