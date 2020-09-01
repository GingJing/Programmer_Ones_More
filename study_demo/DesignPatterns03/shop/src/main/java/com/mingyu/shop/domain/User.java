package com.mingyu.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements Serializable {
    /** 用户名 */
    private String username;

    /** 密码 */
    private String password;

    /** 性别 */
    private String sex;

    /** 角色 */
    private String role;

    /** 等级 */
    private Integer level;
}
