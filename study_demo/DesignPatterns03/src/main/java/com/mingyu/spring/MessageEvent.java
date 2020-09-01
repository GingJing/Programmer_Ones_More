package com.mingyu.spring;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * 消息事件
 *
 * @date: 2020/8/19 9:04
 * @author: GingJingDM
 * @version: 1.0
 */
@Getter
@Setter
public class MessageEvent extends ApplicationEvent {

    private String phone;

    private String message;

    public MessageEvent(Object source) {
        super(source);
    }

    public MessageEvent(Object source, String phone, String message) {
        super(source);
        this.phone = phone;
        this.message = message;
    }
}
