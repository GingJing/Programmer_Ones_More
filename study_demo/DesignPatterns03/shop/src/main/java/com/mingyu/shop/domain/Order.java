package com.mingyu.shop.domain;
import com.mingyu.shop.state.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order implements Serializable {
    /** 商品id */
    private String itemId;

    /** 订单id */
    private String id;

    /** 订单金额 */
    private Integer money;

    /** 实付金额 */
    private Integer paymoney;

    /** 订单状态 */
    private Integer status;

    /** 订单数量 */
    private Integer num;

    /** 用户姓名 */
    private String username;

    /** 优惠券 */
    private String couponsId;

    /** 订单状态 */
    private State state;
}