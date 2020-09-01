package com.mingyu.strategy;

public class OrderMoney {

    /** 将策略接口作为属性 */
    private Strategy strategy;


    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    /**
     * 价格计算
     *
     * @param money   金额
     * @return 金额
     */
    public Integer moneySum(Integer money){
        return strategy.money(money);
    }
}