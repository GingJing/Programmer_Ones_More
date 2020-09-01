package com.mingyu.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Coupons implements Serializable {
    /** 优惠券id */
    private String id;

    /** 用户名 */
    private String username;

    /** 金额 */
    private Integer money;

    /** 状态，1：未使用，2：已使用 */
    private Integer status;

    /** 使用时间 */
    private Date useTime;
}
