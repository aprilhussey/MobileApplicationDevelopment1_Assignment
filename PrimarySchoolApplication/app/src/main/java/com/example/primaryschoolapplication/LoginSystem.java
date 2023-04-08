package com.example.primaryschoolapplication;

import java.util.ArrayList;

public class LoginSystem
{
    // Declare variables
    ArrayList<User> users;
    public static User loggedInUser;

    public LoginSystem()
    {
        users = new ArrayList<>();
        loggedInUser = null;
    }

    // Checks if the userID entered matches one in the list,
    // then checks if the password entered matches the password
    // associated with that userID. Returns a bool
    public boolean login(String userID, String password)
    {
        for (User user : users)
        {
            if (user.getUserID().equals(userID))
            {
                if (user.getPassword().equals(password))
                {
                    loggedInUser = user;
                    return true;
                }
            }
        }
        return false;
    }

    public static User getLoggedInUser()
    {
        return loggedInUser;
    }

    public void saveUser(User US)
    {
        users.add(US);
    }

    public void modifyUser(String userID, String newPassword, String newFirstName, String newLastName)
    {
        for (User user : users)
        {
            if (user.getUserID().equals(userID))
            {
                user.setPassword(newPassword);
                user.setFirstName(newFirstName);
                user.setLastName(newLastName);
                break;
            }
        }
    }

    public void deleteUser(String userID)
    {
        for (int i = 0; i < users.size(); i++)
        {
            if (users.get(i).getUserID().equals(userID))
            {
                users.remove(i);
                break;
            }
        }
    }

    public ArrayList<User> getUsers()
    {
        return users;
    }
}
