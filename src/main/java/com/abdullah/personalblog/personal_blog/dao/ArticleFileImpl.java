package com.abdullah.personalblog.personal_blog.dao;

import com.abdullah.personalblog.personal_blog.model.Article;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ArticleFileImpl implements ArticleDAO{
    ObjectMapper mapper;

    @Autowired
    public ArticleFileImpl(ObjectMapper theMapper){
        mapper = theMapper;
        mapper.registerModule(new JavaTimeModule());
    }

    @Override
    public List<Article> findAll() {
        // Create articles list and mapper
        List<Article> articles = new ArrayList<>();

        // List all files in data directory
        File dataDirectory = new File("data");
        if(dataDirectory.exists() && dataDirectory.isDirectory()){
            File[] files = dataDirectory.listFiles();

            // For each file read it and store it in the articles list
            assert files != null;
            for(File file: files){
                try {
                    Article article = mapper.readValue(file, Article.class);
                    articles.add(article);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        } else{
            System.out.println("No data directory");
        }

        return articles;
    }

    @Override
    public Article findById(String theId) {
        return null;
    }

    @Override
    public void save(Article theArticle) {

    }
}
