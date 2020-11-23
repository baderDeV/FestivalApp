package com.example.myappfestival.business.interfaces;

import com.example.myappfestival.model.User;

public interface UserInterface {
    public int createUser(String username,String password);
    public User loginUser(String username, String password);
}
