package com.abdullah.personalblog.personal_blog.controller;

import com.abdullah.personalblog.personal_blog.dao.ArticleDAO;
import com.abdullah.personalblog.personal_blog.model.Article;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    ArticleDAO articleDAO;

    @Autowired
    public HomeController(ArticleDAO theArticleDAO){
        articleDAO = theArticleDAO;
    }

    @GetMapping("/")
    public String showHome(Model theModel){
        List<Article> articles = articleDAO.findAll();
        theModel.addAttribute("articles", articles);
        return "home-page";
    }

    @GetMapping("/new")
    public String showNewArticleForm(Model theModel){
        theModel.addAttribute("article", new Article());
        return "article-form";
    }

    @PostMapping("/processArticleForm")
    public String processArticleForm(@ModelAttribute("article") Article theArticle) {
        articleDAO.save(theArticle);
        return "redirect:/admin";
    }

    @GetMapping("/article/{articleId}")
    public String showArticle(@PathVariable("articleId") String articleId, Model theModel){
        Article article = articleDAO.findById(articleId);
        theModel.addAttribute("article", article);
        return "article-page";
    }

    @GetMapping("/admin")
    public String showAdminPage(Model theModel){
        List<Article> articles = articleDAO.findAll();
        theModel.addAttribute("articles", articles);
        return "admin-page";
    }
}
