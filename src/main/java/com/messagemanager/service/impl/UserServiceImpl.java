/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.messagemanager.service.impl;

import com.messagemanager.dao.UserDAO;
import com.messagemanager.domain.User;
import com.messagemanager.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Dimon
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Transactional(readOnly = true)
    @Override
    public User getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAll() {
        return userDAO.getAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void add(User user) {
        userDAO.add(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void update(User user) {
        userDAO.update(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void delete(User user) {
        userDAO.delete(user);
    }

    @Override
    public User getUserByUserName(String name) {
        return userDAO.getUserByUserName(name);
    }
}
