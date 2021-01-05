package com.bro.spring_boot.service;

import com.bro.spring_boot.entities.User;

import java.util.List;

public interface UserService {

    public List<User> getAllUsers();

    public void saveUser(User user);

    public void editUser(User user);

    public User getUser(int id);

    public User getUserByName(String username);

    public void deleteUser(int id);
}