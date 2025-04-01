package com.example.dao;

import com.example.model.User;

import java.util.List;

public interface UserDao {
    User findById(Long id);
    List<User> findAll();
    void save(User user);
    void deleteById(Long id);
}