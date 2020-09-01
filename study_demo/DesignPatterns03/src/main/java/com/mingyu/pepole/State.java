package com.mingyu.pepole;

/**
 * 状态接口
 *
 * @date: 2020/8/19 9:04
 * @author: GingJingDM
 * @version: 1.0
 */
public interface State {

    /***
     * 改变状态
     * @param people：要改变状态的对象
     * @param state：变更成指定的状态
     */
    void change(People people,State state);

    //执行的行为
    void execute();
}