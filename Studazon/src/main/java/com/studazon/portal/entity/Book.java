package com.studazon.portal.entity;


import java.util.Arrays;

public class Book {
    private int id;
    private int userId;
    private String title;
    private String author;
    private String ISBN;
    private String book_condition;
    private byte[] imageUrl;
    private String comments;
    private double price;


    public Book(int id, int userId, String title, String author, String ISBN, String book_condition, byte[] imageUrl, String comments, double price) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.book_condition = book_condition;
        this.imageUrl = imageUrl;
        this.comments = comments;
        this.price = price;
    }

    public Book(int userId, String title, String author, String ISBN, String book_condition, byte[] imageUrl, String comments, double price) {
        this.userId = userId;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.book_condition = book_condition;
        this.imageUrl = imageUrl;
        this.comments = comments;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", book_condition='" + book_condition + '\'' +
                ", imageUrl='" + Arrays.toString(imageUrl) + '\'' +
                ", comments='" + comments + '\'' +
                ", price='" + price + '\'' +
                '}';
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

    public byte[] getImageUrl() {
        return imageUrl;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setImageUrl(byte[] imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}