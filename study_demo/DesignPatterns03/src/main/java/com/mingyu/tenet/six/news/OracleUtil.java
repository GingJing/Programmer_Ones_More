package com.mingyu.tenet.six.news;

import java.sql.Connection;

public class OracleUtil extends MySQLUtil {

    //获得Oracle链接
    @Override
    public Connection getConn(){
        System.out.println("链接Oracle");
        return null;
    }
}
