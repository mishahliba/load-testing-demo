package com.example.demo;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class MongoService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Value("classpath:sample.json")
    Resource resourceFile;

    public void insert(String collectionName) {
        try {
            File json = resourceFile.getFile();
            String content = new String(Files.readAllBytes(json.toPath()));
            System.out.println(content);
            MongoCollection<Document> collection = mongoTemplate.getCollection(collectionName);
            if (collection != null) {
                Document doc = Document.parse(content);
                collection.insertOne(doc);
                System.out.println("Successfully created document");
            } else {
                mongoTemplate.createCollection(collectionName);
                Document doc = Document.parse(content);
                collection.insertOne(doc);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void update(Document query, Document update, String collectionName) {
        MongoCollection<Document> collection = mongoTemplate.getCollection(collectionName);
        collection.updateOne(query, update);
    }

    public void delete(Document query, String collectionName) {
        MongoCollection<Document> collection = mongoTemplate.getCollection(collectionName);
        collection.deleteOne(query);
    }

}
