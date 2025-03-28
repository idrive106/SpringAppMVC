package com.example.service;

import com.example.model.User;
import com.example.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService{

    private final UserRepositories userRepositories;

    @Autowired
    public UserService(UserRepositories userRepositories) {
        this.userRepositories = userRepositories;
    }

    public List<User> findAll() {
        return userRepositories.findAll();
    }

    public User findOne(Long id) {
        Optional<User> foundPerson = userRepositories.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(User user) {
        userRepositories.save(user);
    }

    @Transactional
    public void update(Long id, User updatedUser) {
        updatedUser.setId(id);
        userRepositories.save(updatedUser);
    }

    @Transactional
    public void delete(Long id) {
        userRepositories.deleteById(id);
    }
}