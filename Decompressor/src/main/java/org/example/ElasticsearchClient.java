package org.example;

import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.apache.http.HttpHost;
import org.elasticsearch.common.xcontent.XContentType;

import java.util.List;
import java.util.Map;

public class ElasticsearchClient {
    private final RestHighLevelClient client;

    public ElasticsearchClient() {
        this.client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));
    }

    public RestHighLevelClient getClient() {
        return client;
    }

    public void indexDocument(String indexName, Map<String, String> document) {
        try {
            IndexRequest request = new IndexRequest(indexName)
                    .source(document, XContentType.JSON);
            client.index(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void indexBulk(String indexName, List<Map<String, String>> documents) {
        try {
            BulkRequest bulkRequest = new BulkRequest();
            for (Map<String, String> document : documents) {
                bulkRequest.add(new IndexRequest(indexName)
                        .source(document, XContentType.JSON));
            }
            client.bulk(bulkRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
