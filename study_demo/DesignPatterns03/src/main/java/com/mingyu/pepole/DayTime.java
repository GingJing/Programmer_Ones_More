package com.mingyu.pepole;

public class DayTime implements State {

    /**
     * 改变状态-白天
     * @param people：要改变状态的对象
     * @param state：变更成指定的状态
     */
    @Override
    public void change(People people, State state) {
        people.setState(state);
        //立刻调用
        //execute();
    }

    @Override
    public void execute(){
        System.out.println("白天要吃饭！");
    }
}