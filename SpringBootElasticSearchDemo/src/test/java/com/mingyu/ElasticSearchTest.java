package com.mingyu;

import com.alibaba.fastjson.JSON;
import com.mingyu.pojo.User;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * es测试类
 *
 * @author: GingJingDM
 * @date: 2020年 10月08日 22时02分
 * @version: 1.0
 */
@SpringBootTest
public class ElasticSearchTest {

    private String index = "test_springboot";

    private String userIndex = "user";

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    public void testCreateIndex() throws IOException {
        CreateIndexRequest indexRequest = new CreateIndexRequest(userIndex);
        CreateIndexResponse indexResponse = restHighLevelClient.indices().create(indexRequest, RequestOptions.DEFAULT);
        System.out.println(indexResponse);
    }

    @Test
    public void testGetIndex() throws IOException {
        GetIndexRequest indexRequest = new GetIndexRequest(index);
        boolean exists = restHighLevelClient.indices().exists(indexRequest, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    @Test
    public void testDeleteIndex() throws IOException {
        DeleteIndexRequest indexRequest = new DeleteIndexRequest(userIndex);
        AcknowledgedResponse response = restHighLevelClient.indices().delete(indexRequest, RequestOptions.DEFAULT);
        System.out.println(response.isAcknowledged());
    }

    @Test
    public void testAddDocument() throws IOException {

        User user = new User("明", 22);
        IndexRequest indexRequest = new IndexRequest(userIndex);
        indexRequest.id("1").type("_doc")
                .timeout(TimeValue.timeValueSeconds(1))
                .source(JSON.toJSONString(user), XContentType.JSON);
        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(indexResponse + System.lineSeparator() + indexResponse.status());
    }

    @Test
    public void testExistsDocument() throws IOException {
        GetRequest getRequest = new GetRequest(userIndex, "_doc","1");
        // 不将获取_source中的上下文
        getRequest.fetchSourceContext(new FetchSourceContext(false)).storedFields("_none_");
        boolean exists = restHighLevelClient.exists(getRequest, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    @Test
    public void testGetDocument() throws IOException {
        GetRequest getRequest = new GetRequest(userIndex, "_doc","1");
//        getRequest.fetchSourceContext(new FetchSourceContext(false)).storedFields("_none_");
        GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(getResponse.getSourceAsString());
        System.out.println(getResponse);
    }

    @Test
    public void testUpdateDocument() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest(userIndex, "_doc","1");
        User user = new User("鸣", 18);
        updateRequest.doc(JSON.toJSONString(user), XContentType.JSON);
        UpdateResponse updateResponse = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println(updateResponse);
    }

    @Test
    public void testDeleteDocument() throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest(userIndex, "_doc","1");
        deleteRequest.timeout("1s");
        DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println(deleteResponse);
    }

    @Test
    public void testBulkRequest() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout(TimeValue.timeValueSeconds(10));

        List<User> users = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            users.add(new User("test" + i, i));
        }
        for (int i = 0; i < users.size(); i++) {
            IndexRequest indexRequest = new IndexRequest(userIndex)
                    .id("" + (i + 1))
                    .type("_doc")
                    .source(JSON.toJSONString(users.get(i)), XContentType.JSON);
            bulkRequest.add(indexRequest);
        }
        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulkResponse);
    }

    @Test
    public void testSearch() throws IOException {
        SearchRequest searchRequest = new SearchRequest(userIndex);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "test2");
//        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name", "test");
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        searchSourceBuilder.query(matchAllQueryBuilder)
                .timeout(new TimeValue(60, TimeUnit.SECONDS));

        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(searchResponse.getHits()));
        SearchHit[] hits = searchResponse.getHits().getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit);
        }
    }
}
