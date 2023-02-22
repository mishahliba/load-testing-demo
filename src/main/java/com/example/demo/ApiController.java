package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private MongoService mongoService;

    @Autowired
    private ElasticService elasticService;


    @GetMapping("/create_doc")
    public void createDocument(@RequestParam String collectionName) {
        mongoService.insert(collectionName);
    }

    @GetMapping("/create_elastic")
    public void createElasticIndex(@RequestParam String indexName) {
        elasticService.createIndex(indexName);
    }

}
