package com.mingyu.tenet.five.news;

/*****
 * @Author: http://www.itheima.com
 * @Description: com.itheima.tenet.five.old.ReportExcelClient
 ****/
public class ReportExcelClient {
    public static void main(String[] args) {
        ReportInterface reportInterface= new DataReadImpl();
        reportInterface.reportExcel();
    }
}
