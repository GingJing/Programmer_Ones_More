package com.mingyu.tenet.three.news;

/*****
 * @Author: http://www.itheima.com
 * @Description: com.itheima.tenet.three.news.Customer
 ****/
public abstract class Customer {

    private String name;

    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public abstract String sefInfo();
}
