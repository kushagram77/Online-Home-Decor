package com.yash.onlinehomedecor.service;

import com.yash.onlinehomedecor.dao.UserDAO;
import com.yash.onlinehomedecor.domain.User;
import com.yash.onlinehomedecor.exception.UserBlockedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public void register(User u) {
        userDAO.save(u);
    }

    @Override
    public User login(String email, String password) throws UserBlockedException {
        try {
            User u = userDAO.findByProperty("email", email).get(0);
            if (!u.getPassword().equals(password)) {
                throw new UserBlockedException("Invalid password");
            }
            return u;
        } catch (EmptyResultDataAccessException ex) {
            throw new UserBlockedException("Invalid email");
        }
    }

    @Override
    public List<User> getUserList() {
        return userDAO.findAll();
    }

    @Override
    public void update(User u) {
        userDAO.update(u);
    }

    @Override
    public void delete(Integer userId) {
        userDAO.delete(userId);
    }

    @Override
    public User findById(Integer userId) {
        return userDAO.findById(userId);
    }

    @Override
    public List<User> findByProperty(String propName, Object propValue) {
        return userDAO.findByProperty(propName, propValue);
    }
}