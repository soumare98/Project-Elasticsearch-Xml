package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;


@FunctionalInterface
public interface DataProcessor {

    void processDocument(Map<String, String> document);
}
