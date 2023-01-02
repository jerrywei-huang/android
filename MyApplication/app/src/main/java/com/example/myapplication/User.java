package com.example.myapplication;

public class User {

    private String username;
    private String password;
    private String hobby;
    private String gender;
    private String position;
    private String marrige;

    public User() {
    }

    public User(String username, String password, String hobby, String gender, String position, String marrige) {
        this.username = username;
        this.password = password;
        this.hobby = hobby;
        this.gender = gender;
        this.position = position;
        this.marrige = marrige;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMarrige() {
        return marrige;
    }

    public void setMarrige(String marrige) {
        this.marrige = marrige;
    }
}

