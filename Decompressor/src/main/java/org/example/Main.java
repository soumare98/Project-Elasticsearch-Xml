package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world...");
        String zipPath = "src/main/resources/xml.zip";
        String outputDir = "target/output/";
        String indexName = "documents_" + LocalDate.now();

        try {
            String xmlFilePath = Extaction.extractXML(zipPath, outputDir);
            if (xmlFilePath == null || xmlFilePath.isEmpty()) {
                System.err.println("Erreur : Impossible de trouver le fichier XML dans l'archive.");
                return;
            }

            System.out.println("Fichier XML extrait : " + xmlFilePath);

            List<String> documents = new ArrayList<>();
            XMLParser parser = new XMLParser();
            ObjectMapper objectMapper = new ObjectMapper();

            parser.parse(xmlFilePath, document -> {
                try {
                    String jsonDocument = objectMapper.writeValueAsString(document);
                    documents.add(jsonDocument);
                } catch (Exception e) {
                    System.err.println("Erreur lors de la conversion d'un document en JSON : " + e.getMessage());
                }
            });

            System.out.println("Nombre de documents analysés : " + documents.size());
            if (documents.isEmpty()) {
                System.err.println("Aucun document à indexer.");
                return;
            }

            ElasticsearchClient esClient = new ElasticsearchClient();
            indexManager indexManager = new indexManager();

            try {

                if (!indexManager.createIndex(esClient.getClient(), indexName)) {
                    System.err.println("Erreur lors de la création de l'index.");
                    return;
                }

                BulkIndexer.bulkIndex(esClient.getClient(), documents, indexName);
                System.out.println("Indexation terminée avec succès !");
            } finally {
                esClient.close();
            }

        } catch (Exception e) {
            System.err.println("Erreur lors de l'exécution : " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("Fin du traitement.");
    }
}