package com.mingyu.tenet.four;


public class ChartDisplay {

    private AbstractChart abstractChart;

    //展示报表
    public void showChart(){
        abstractChart.display();
    }

    //Setter注入
    public void setAbstractChart(AbstractChart abstractChart) {
        this.abstractChart = abstractChart;
    }
}
