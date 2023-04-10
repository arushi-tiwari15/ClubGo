package com.example.mychat;
public class chat {
    String username;
    String password;
    public chat(){
    }
    public chat(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setMessage(String password) {
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}


