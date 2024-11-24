package org.example;

import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.util.List;

public class BulkIndexer {
    public static void bulkIndex(RestHighLevelClient client, List<String> documents, String indexName) {
        if (documents.isEmpty()) {
            System.out.println("Aucun document à indexer.");
            return;
        }

        BulkRequest bulkRequest = new BulkRequest();
        for (String documentJson : documents) {
            bulkRequest.add(new IndexRequest(indexName)
                    .source(documentJson, XContentType.JSON));
        }

        try {
            BulkResponse bulkResponse = client.bulk(bulkRequest, RequestOptions.DEFAULT);

            if (bulkResponse.hasFailures()) {
                System.out.println("Échec de l'indexation en masse : " + bulkResponse.buildFailureMessage());
            } else {
                System.out.println("Indexation en masse réussie !");
            }

            long successfulIndexCount = 0;
            for (BulkItemResponse item : bulkResponse.getItems()) {
                if (!item.isFailed()) {
                    successfulIndexCount++;
                }
            }
            System.out.println("Nombre de documents indexés avec succès : " + successfulIndexCount);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
