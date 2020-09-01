package com.mingyu.shop.proxy;

/**
 * 文件上传接口
 *
 * @date: 2020/8/26 8:57
 * @author: GingJingDM
 * @version: 1.0
 */
public interface FileUpload {

    /**
     * 上传文件
     *
     * @param buffers  文件字节数组
     * @param extName  文件后缀
     * @return 文件全路径
     */
    String upload(byte[] buffers , String extName);
}
