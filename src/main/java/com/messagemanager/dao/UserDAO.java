package com.messagemanager.dao;

import com.messagemanager.domain.User;

import java.util.List;

/**
 * @author Dimon
 */
public interface UserDAO {
    public User getUserById(Long id);

    public List<User> getAll();

    public void add(User user);

    public void update(User user);

    public void delete(User user);

    public User getUserByUserName(String name);
}
