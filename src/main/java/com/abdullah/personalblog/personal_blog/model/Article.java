package com.abdullah.personalblog.personal_blog.model;

import java.time.LocalDate;
import java.util.UUID;

public class Article {
    private String id;
    private String title;
    private String content;
    private LocalDate dateOfPublication;

    public Article() {
        this.id = UUID.randomUUID().toString();
    }

    public Article(String title, String content, LocalDate dateOfPublication) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.content = content;
        this.dateOfPublication = dateOfPublication;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(LocalDate dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", dateOfPublication=" + dateOfPublication +
                '}';
    }
}
