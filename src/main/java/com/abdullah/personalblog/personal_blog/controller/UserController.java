package com.abdullah.personalblog.personal_blog.controller;

import com.abdullah.personalblog.personal_blog.dao.ArticleDAO;
import com.abdullah.personalblog.personal_blog.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    ArticleDAO articleDAO;

    @Autowired
    public UserController(ArticleDAO theArticleDAO){
        articleDAO = theArticleDAO;
    }

    @GetMapping("/")
    public String showHome(Model theModel){
        List<Article> articles = articleDAO.findAll();
        theModel.addAttribute("articles", articles);
        return "home-page";
    }

    @GetMapping("/article/{articleId}")
    public String showArticle(@PathVariable("articleId") String articleId, Model theModel){
        Article article = articleDAO.findById(articleId);
        theModel.addAttribute("article", article);
        return "article-page";
    }
}
