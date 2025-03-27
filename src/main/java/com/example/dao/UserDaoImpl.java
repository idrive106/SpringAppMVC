package com.example.dao;

import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {


    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @Transactional
    public void saveUser(User user) {
        try {
            entityManager.persist(user);
        } catch (RuntimeException e) {
            throw new RuntimeException("Error saving user: " + user, e);
        }
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        try {
            User user = entityManager.find(User.class, id);
            if (user != null) {
                entityManager.remove(user);
            } else {

                System.out.println("User with ID " + id + " not found.");
            }
        } catch (RuntimeException e) {

            throw new RuntimeException("Error deleting user with ID: " + id, e);
        }
    }

    @Override
    @Transactional
    public void editUser(User user) {
        try {
            entityManager.merge(user);
        } catch (RuntimeException e) {

            throw new RuntimeException("Error editing user: " + user, e);
        }
    }

    @Override
    public List<User> viewUsers() {
        try {
            return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
        } catch (RuntimeException e) {

            throw new RuntimeException("Error retrieving users", e);
        }
    }

    @Override
    public User findUser(Long id) {
        try {
            return entityManager.find(User.class, id);
        } catch (RuntimeException e) {

            throw new RuntimeException("Error finding user with ID: " + id, e);
        }
    }

}
