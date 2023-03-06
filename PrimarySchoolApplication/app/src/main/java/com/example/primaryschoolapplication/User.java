package com.example.primaryschoolapplication;

public class User
{
    private String firstName = "";
    private String lastName = "";
    private String username = "";
    private String password = "";
    private String email = "";

    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
    }
}
