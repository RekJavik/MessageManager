/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.messagemanager.dao.impl;

import com.messagemanager.dao.UserDAO;
import com.messagemanager.domain.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Dimon
 */
@Repository
public class UserDAOImpl implements UserDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public User getUserById(Long id) {
        List<User> listMsg = hibernateTemplate.findByNamedParam(" from User where id = :id", "id", id);
        return listMsg == null || listMsg.isEmpty() ? new User() : listMsg.get(0);
    }

    @Override
    public List<User> getAll() {
        return hibernateTemplate.find("from User");
    }

    @Override
    public void add(User user) {
        hibernateTemplate.save(user);
        hibernateTemplate.flush();
    }

    @Override
    public void update(User user) {
        hibernateTemplate.saveOrUpdate(user);
        hibernateTemplate.flush();
    }

    @Override
    public void delete(User user) {
        hibernateTemplate.delete(user);
        hibernateTemplate.flush();
    }

    @Override
    public User getUserByUserName(String name) {
        List<User> listMsg = hibernateTemplate.findByNamedParam(" from User where name = :name", "name", name);
        return listMsg == null || listMsg.isEmpty() ? null : listMsg.get(0);
    }

}
