package com.mingyu.shop.controller;

import com.mingyu.shop.proxy.FileUploadProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件控制器
 *
 * @date: 2020/8/26 9:49
 * @author: GingJingDM
 * @version: 1.0
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileUploadProxy fileUploadProxy;


    /***
     * 文件上传
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/upload")
    public String upload(MultipartFile file) throws Exception {
        return fileUploadProxy.upload(file);
    }
}
