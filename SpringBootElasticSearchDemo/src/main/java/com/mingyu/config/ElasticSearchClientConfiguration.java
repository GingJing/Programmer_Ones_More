package com.mingyu.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * es客户端配置
 *
 * @author: GingJingDM
 * @date: 2020年 10月08日 21时58分
 * @version: 1.0
 */
@Configuration
public class ElasticSearchClientConfiguration {

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("127.0.0.1", 9200, "http"))
        );
        return restHighLevelClient;
    }
}
