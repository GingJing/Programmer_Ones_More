package com.mingyu.shop.proxy;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.mingyu.shop.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.util.UUID;

/**
 * 阿里云文件上传实现
 *
 * @date: 2020/8/26 8:58
 * @author: GingJingDM
 * @version: 1.0
 */
@Component(value = "aliyunOSSFileUpload")
public class AliyunOSSFileUpload implements FileUpload {

    @Value("${aliyun.oss.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.accessKey}")
    private String accessKey;
    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${aliyun.oss.key}")
    private String key;
    @Value("${aliyun.oss.bucketName}")
    private String bucketName;
    @Value("${aliyun.oss.backurl}")
    private String backurl;

    @Override
    public String upload(byte[] buffers, String extName) {
        String realName = UUID.randomUUID().toString()+"."+extName ;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKey, accessKeySecret);

        // <yourObjectName>表示上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key + realName, new ByteArrayInputStream(buffers));

        // 上传字符串
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(FileUtil.getContentType("." + extName));
        putObjectRequest.setMetadata(objectMetadata);
        ossClient.putObject(putObjectRequest);

        // 关闭OSSClient
        ossClient.shutdown();
        return backurl + realName;
    }
}
