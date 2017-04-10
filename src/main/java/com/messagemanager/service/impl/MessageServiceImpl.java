/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.messagemanager.service.impl;

import com.messagemanager.dao.MessageDAO;
import com.messagemanager.domain.Message;
import com.messagemanager.domain.User;
import com.messagemanager.service.MessageService;
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
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageDAO messageDAO;

    @Autowired
    private UserService userDAO;

    @Transactional(readOnly = true)
    @Override
    public Message getMessageById(Long id) {
        return messageDAO.getMessageById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Message> getAll() {
        return messageDAO.getAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void add(Message message) {
        User us = userDAO.getUserByUserName(message.getUser().getName());
        if (us == null) {
            us = new User();
            us.setName(message.getUser().getName());
            userDAO.add(us);
        }
        message.setUser(us);
        messageDAO.add(message);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void update(Message message) {
        messageDAO.update(message);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void delete(Message message) {
        messageDAO.delete(message);
    }
}
