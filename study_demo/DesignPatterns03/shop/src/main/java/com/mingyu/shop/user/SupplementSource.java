package com.mingyu.shop.user;

/**
 * 享元组件逻辑对象
 *
 * @date: 2020/8/27 8:41
 * @author: GingJingDM
 * @version: 1.0
 */
public class SupplementSource extends AbstractLogComponent {

    public SupplementSource(String username, String sex, String role, Integer level) {
        super(username, sex, role, level);
    }

    /**
     * 业务逻辑，完善不同方法的日志记录
     *
     * @param args 长度为2，第1个是方法名字，第2个是方日志信息
     */
    @Override
    void supplementLogContent(String... args) {
        super.setMethodName(args[0]);
        super.setMessage(args[1]);
    }
}
