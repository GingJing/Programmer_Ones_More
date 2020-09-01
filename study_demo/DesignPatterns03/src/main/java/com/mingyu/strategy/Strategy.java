package com.mingyu.strategy;

/**
 * 策略接口
 *
 * @date: 2020/8/25 8:42
 * @author: GingJingDM
 * @version: 1.0
 */
public interface Strategy {

    /**
     * 金额计算
     *
     * @param money   金额
     * @return
     */
    Integer money(Integer money);
}
