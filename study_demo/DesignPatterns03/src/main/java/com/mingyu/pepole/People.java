package com.mingyu.pepole;

public class People {

    /** 人拥有指定状态 */
    private State state;

    public People() {
        this.state = null;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}