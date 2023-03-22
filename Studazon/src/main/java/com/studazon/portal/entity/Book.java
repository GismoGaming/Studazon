package com.studazon.portal.entity;


public class Book {
    private int id;
    private int userId;
    private String title;
    private String author;
    private String ISBN;
    private String book_condition;
    private String imageUrl;
    private String comments;


    public Book(int id, int userId, String title, String author, String ISBN, String book_condition, String imageUrl, String comments) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.book_condition = book_condition;
        this.imageUrl = imageUrl;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getBook_condition() {
        return book_condition;
    }

    public void setBook_condition(String book_condition) {
        this.book_condition = book_condition;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
