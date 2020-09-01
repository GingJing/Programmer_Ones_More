package com.mingyu.shop.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 日志共享组件抽象类
 *
 * @date: 2020/8/27 8:36
 * @author: GingJingDM
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public abstract class AbstractLogComponent {
    /**********************************************************
     * 同一个线程中，记录日志时，username、sex、role相同
     **********************************************************/
    /** 用户姓名 */
    private String username;

    /** 用户性别 */
    private String sex;

    /** 用户角色 */
    private String role;

    /** 等级 */
    private Integer level;

    /**********************************************************
     * 同一个线程中，记录日志时，每次访问的不同方法和参数不一样
     **********************************************************/
    /** 操作方法 */
    private String methodName;

    /** 信息 */
    private String message;

    /**********************************************************
     * 业务操作,补充和完善methodName，args参数
     **********************************************************/
    abstract void supplementLogContent(String... args);

    /**
     * 对username、sex、role赋值[这些是同一个线程中不变的数据]
     */
    public AbstractLogComponent(String username, String sex, String role, Integer level) {
        this.username = username;
        this.sex = sex;
        this.role = role;
        this.level = level;
    }
}
