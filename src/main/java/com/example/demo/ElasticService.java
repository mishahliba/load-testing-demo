package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class ElasticService {

    @Value("classpath:sample.json")
    Resource resourceFile;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    public void createIndex(String indexName) {
        File json = null;
        try {
            json = resourceFile.getFile();
            String jsonString = new String(Files.readAllBytes(json.toPath()));

            IndexQuery indexQuery = new IndexQueryBuilder()
                    .withSource(jsonString)
                    .withIndex(indexName)
                    .build();

            elasticsearchOperations.index(indexQuery, IndexCoordinates.of(indexName));
            String documentId = indexQuery.getId();

            System.out.println("Inserted document with ID: " + documentId);
            System.out.println("Inserted into index: " + indexName);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
