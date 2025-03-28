package com.example.dao;

import com.example.model.User;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveUser(User user) {
            entityManager.persist(user);
    }

    @Override
    public void deleteUser(Long id) {
            User user = entityManager.find(User.class, id);
            if (user != null) {
                entityManager.remove(user);
            } else {
                System.out.println("User with ID " + id + " not found.");
            }
    }

    @Override
    public void editUser(User user) {
            entityManager.merge(user);
    }

    @Override
    public List<User> viewUsers() {
            return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public User findUser(Long id) {
            return entityManager.find(User.class, id);
    }
}