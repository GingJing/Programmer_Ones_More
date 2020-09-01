package com.mingyu.decorator;

public class CocoTea extends DecoratorTea {

    @Override
    public void making() {
        //调用被扩展的对象方法
        super.making();

        //增加扩展
        addCoco();
    }

    //扩展
    public void addCoco(){
        System.out.println("添加椰汁！");
    }
}