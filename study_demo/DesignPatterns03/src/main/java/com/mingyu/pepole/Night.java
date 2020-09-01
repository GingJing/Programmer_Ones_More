package com.mingyu.pepole;

public class Night implements State {

    /**
     * 改变状态-白天
     * @param people：要改变状态的对象
     * @param state：变更成指定的状态
     */
    @Override
    public void change(People people, State state) {
        people.setState(state);
    }

    @Override
    public void execute() {
        System.out.println("晚上要睡觉！");
    }
}