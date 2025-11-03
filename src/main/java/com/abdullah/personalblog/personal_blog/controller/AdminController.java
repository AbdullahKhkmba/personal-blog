package com.abdullah.personalblog.personal_blog.controller;

import com.abdullah.personalblog.personal_blog.dao.ArticleDAO;
import com.abdullah.personalblog.personal_blog.model.Article;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    ArticleDAO articleDAO;

    @Autowired
    public AdminController(ArticleDAO theArticleDAO){
        articleDAO = theArticleDAO;
    }

    @GetMapping("")
    public String showAdminPage(Model theModel){
        List<Article> articles = articleDAO.findAll();
        theModel.addAttribute("articles", articles);
        return "admin-page";
    }

    @GetMapping("/new")
    public String showNewArticleForm(Model theModel){
        theModel.addAttribute("article", new Article());
        return "article-form";
    }

    @PostMapping("/processArticleForm")
    public String processArticleForm(@Valid @ModelAttribute("article") Article theArticle,
                                     BindingResult theBindingResult) {
        if(theBindingResult.hasErrors()){
            return "article-form";
        }
        else{
            articleDAO.save(theArticle);
            return "redirect:/admin";
        }
    }

    @GetMapping("/edit/{articleId}")
    public String edit(@PathVariable("articleId") String articleId, Model theModel){
        Article article = articleDAO.findById(articleId);
        theModel.addAttribute("article", article);
        theModel.addAttribute("action", "Update");
        return "article-form";
    }

    @GetMapping("/delete/{articleId}")
    public String delete(@PathVariable("articleId") String articleId){
        articleDAO.deleteById(articleId);
        return "redirect:/admin";
    }
}
