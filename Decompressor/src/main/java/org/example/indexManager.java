package org.example;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;

public class indexManager {
    public boolean createIndex(RestHighLevelClient client, String indexName) {
        try {

            GetIndexRequest getIndexRequest = new GetIndexRequest(indexName);
            boolean exists = client.indices().exists(getIndexRequest, RequestOptions.DEFAULT);

            if (exists) {
                System.out.println("Index déjà existant : " + indexName);
                return true;
            }

            CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
            CreateIndexResponse response = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
            return response.isAcknowledged();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteIndex(RestHighLevelClient client, String indexName) {
        try {
            DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(indexName);
            client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
            System.out.println("Index supprimé : " + indexName);
            return true;
        } catch (Exception e) {
            System.err.println("Impossible de supprimer l'index : " + indexName);
            e.printStackTrace();
            return false;
        }
    }


}
