package com.bro.spring_boot.service;

import com.bro.spring_boot.dao.UserDao;
import com.bro.spring_boot.entities.Role;
import com.bro.spring_boot.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public List<Role> getAllRoles() {
        return userDao.getAllRoles();
    }

    @Override
//    @Transactional
    public void saveUser(User user) {
        setUserRoles(user);
        userDao.saveUser(user);
    }

    @Override
//    @Transactional
    public void editUser(User user) {
        setUserRoles(user);
        userDao.editUser(user);
    }

    @Override
    public User getUser(int id) {
        return userDao.getUser(id);
    }

    @Override
    public User getUserByName(String username) {
        return userDao.getUserByName(username);
    }

    @Override
//    @Transactional
    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }

//    @Transactional
    public void setUserRoles(User user) {
        user.setRoles(user
                .getRoles()
                .stream()
                .map(role -> userDao.getRoleByName(role.getName()))
                .collect(Collectors.toSet()));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userDao.getUserByName(username);

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), user.getRoles());
    }
}