package com.studazon.portal.entity;

public class User {
    private int id;
    private String fullname;
    private String email;
    private String password;
    private String secret_question;
    private String secret_answer;

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecret_answer() {
        return secret_answer;
    }

    public void setSecret_answer(String secret_answer) {
        this.secret_answer = secret_answer;
    }

    public String getSecret_question() {
        return secret_question;
    }

    public void setSecret_question(String secret_question) {
        this.secret_question = secret_question;
    }

    public User(String fullname, String email, String password, String secret_question, String secret_answer) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.secret_question = secret_question;
        this.secret_answer = secret_answer;
    }

}
