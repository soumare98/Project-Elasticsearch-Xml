package org.example;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;

public class indexManager {
    public static void createIndex(RestHighLevelClient client, String indexName) {
        try {
            GetIndexRequest request = new GetIndexRequest(indexName);
            boolean indexExists = client.indices().exists(request, RequestOptions.DEFAULT);

            if (indexExists) {
                System.out.println("L'index existe déjà, suppression de l'index...");
                client.indices().delete(new DeleteIndexRequest(indexName), RequestOptions.DEFAULT);
            }

            CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
            client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
            System.out.println("Index créé : " + indexName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
