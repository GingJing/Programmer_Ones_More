package com.mingyu.shop.proxy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 文件上传代理对象
 *
 * @date: 2020/8/26 9:24
 * @author: GingJingDM
 * @version: 1.0
 */
@Component
@ConfigurationProperties(prefix = "upload")
public class FileUploadProxy implements ApplicationContextAware {

    @Autowired
    private AliyunOSSFileUpload aliyunOSSFileUpload;

    @Autowired
    private FastDFSFileUpload fastDFSFileUpload;

    private ApplicationContext act;

    //aliyunOSSFileUpload -> mp4,avi
    private Map<String, List<String>> filemap;


    /**
     * 上传文件
     * @param file 文件
     * @return 文件存储路径
     * @throws Exception
     */
    public String upload(MultipartFile file) throws Exception {
        //buffers：文件字节数组
        byte[] buffers = file.getBytes();
        //extName：后缀名  1.jpg->jpg
        String fileName = file.getOriginalFilename();
        String extName = StringUtils.getFilenameExtension(fileName);
        if (StringUtils.isEmpty(extName)) {
            return "";
        }
        //循环filemap映射关系对象
        for (Map.Entry<String, List<String>> entry : filemap.entrySet()) {
            //获取指定的value  mp4,avi  |  png,jpg
            List<String> suffixList = entry.getValue();

            //匹配当前用户上传的文件扩展名是否匹配
            for (String suffix : suffixList) {
                if(extName.equalsIgnoreCase(suffix)){
                    //获取指定key   aliyunOSSFileUpload | fastdfsFileUpload
                    //一旦匹配执行文件上传
                    String key = entry.getKey();
                    return act.getBean(key,FileUpload.class).upload(buffers,extName);
                }
            }
        }
        return "";
    }

    //注入映射配置
    public void setFilemap(Map<String, List<String>> filemap) {
        this.filemap = filemap;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.act=applicationContext;
    }
}
