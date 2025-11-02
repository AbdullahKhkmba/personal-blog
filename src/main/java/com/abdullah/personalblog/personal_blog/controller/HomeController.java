package com.abdullah.personalblog.personal_blog.controller;

import com.abdullah.personalblog.personal_blog.model.Article;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @GetMapping("/")
    public String showHome(Model theModel){
        // Create articles list and mapper
        List<Article> articles = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

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

            // Add the articles into the template
            theModel.addAttribute("articles", articles);
        } else{
            System.out.println("No data directory");
        }
        return "home-page";
    }

    @RequestMapping("/new")
    public String showNewArticleForm(Model theModel){
        theModel.addAttribute("article", new Article());
        return "article-form";
    }

    @PostMapping("/processArticleForm")
    public String processArticleForm(@ModelAttribute("article") Article theArticle) {
        // Set date in the article object
        theArticle.setDateOfPublication(LocalDate.now());

        // Create data directory if doesn't exist
        File directory = new File("data");
        if(!directory.exists()){
            boolean created = directory.mkdir();
            if(created){
                System.out.println("Data directory was created successfully");
            }
            else{
                System.out.println("Couldn't create data directory");
            }

        } else{
            System.out.println("Data directory already exist");
        }

        // Write the article object as JSON in a file with the name of the article's title
        ObjectMapper mapper = new ObjectMapper();
        String filename = "article-" + theArticle.getId() + ".json";
        String newFilePath = "data/" + filename;
        mapper.registerModule(new JavaTimeModule());

        try{
            mapper.writeValue(new File(newFilePath), theArticle);
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }

        // Redirect to the home page
        return "redirect:/";
    }

    @GetMapping("/article/{articleId}")
    public String showArticle(@PathVariable("articleId") String articleId, Model theModel){
        // Reconstruct file path
        String filePath = "data/article-" + articleId + ".json";

        // Initialize new file object, and object mapper
        File file = new File(filePath);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModules(new JavaTimeModule());

        try{
            // Read json and store it in Article object
            Article article = mapper.readValue(file, Article.class);

            // Add article object to model
            theModel.addAttribute("article", article);

        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }

        // return article page
        return "article-page";
    }
}
