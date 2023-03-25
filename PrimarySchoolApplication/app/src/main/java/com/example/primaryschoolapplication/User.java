package com.example.primaryschoolapplication;

import java.io.Serializable;

public class User implements Serializable
{
    private static final long serialVersionUID = 1L;    // Unique identifier for the class

    private String userID = "";
    private String password = "";
    private String firstName = "";
    private String lastName = "";
    private String email = "";

    public User(String userID, String password, String firstName, String lastName)
    {
        this.userID = userID;
        this.password = password;

        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getter and Setter functions
    protected String getUserID()
    {
        return userID;
    }

    protected void setUserID(String userID)
    {
        this.userID = userID;
    }

    protected String getPassword()
    {
        return password;
    }

    protected void setPassword(String password)
    {
        this.password = password;
    }

    protected String getFirstName()
    {
        return firstName;
    }

    protected void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    protected String getLastName()
    {
        return lastName;
    }

    protected void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
}
