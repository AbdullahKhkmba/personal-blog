package com.abdullah.personalblog.personal_blog.dao;

import com.abdullah.personalblog.personal_blog.model.Article;

import java.util.List;

public interface ArticleDAO {
    List<Article> findAll();
    Article findById(String theId);
    void save(Article theArticle);
}
