package com.example.service;

import com.example.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {


    void saveUser(User user);

    void deleteUser(Long id);

    void editUser(User user);

    List<User> viewUsers();

    User findUser(Long id);
}
