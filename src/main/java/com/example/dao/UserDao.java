package com.example.dao;

import com.example.model.User;
import java.util.List;

public interface UserDao {
    void saveUser(User user);
    void deleteUser(Long id);
    void editUser(User user);
    List<User> viewUsers();
    User findUser(Long id);
}