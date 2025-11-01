package com.abdullah.personalblog.personal_blog.models;

import java.time.LocalDate;

public class Article {
    private String title;
    private String content;
    private LocalDate dateOfPublication;

    public Article() {

    }

    public Article(String title, String content, LocalDate dateOfPublication) {
        this.title = title;
        this.content = content;
        this.dateOfPublication = dateOfPublication;
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
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", dateOfPublication=" + dateOfPublication +
                '}';
    }
}
