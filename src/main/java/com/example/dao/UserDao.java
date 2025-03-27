package com.example.dao;

import com.example.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    void saveUser(User user);
    void deleteUser(Long id);
    void editUser(User user);
    List<User> viewUsers();
    User findUser(Long id);
}
