package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {


        System.out.println("Hello world!");

        String zipPath = "src/main/resources/xml.zip";
        String outputDir = "target/output/";
        String indexName = "documents_" + java.time.LocalDate.now();


        String xmlFilePath = Extaction.extractXML(zipPath, outputDir);

        List<String> documents = new ArrayList<>();
        XMLParser parser = new XMLParser();
        parser.parse(xmlFilePath, document -> {

            documents.add(new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(document));
        });


        ElasticsearchClient esClient = new ElasticsearchClient();
        indexManager.createIndex(esClient.getClient(), indexName);


        BulkIndexer.bulkIndex(esClient.getClient(), documents, indexName);

        esClient.close();
    }
}