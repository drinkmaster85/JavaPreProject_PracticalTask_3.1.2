package com.bro.spring_boot.service;

import com.bro.spring_boot.entities.Role;
import com.bro.spring_boot.entities.User;

import java.util.List;

public interface UserService {

    public List<User> getAllUsers();

    public List<Role> getAllRoles();

    public void saveUser(User user);

    public void editUser(User user);

    public User getUser(int id);

    public User getUserByName(String username);

    public void deleteUser(int id);
}