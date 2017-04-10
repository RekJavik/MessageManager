/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.messagemanager.dao.impl;

import com.messagemanager.dao.MessageDAO;
import com.messagemanager.domain.Message;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Dimon
 */
@Repository
public class MessageDAOImpl implements MessageDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public Message getMessageById(Long id) {
        List<Message> listMsg = hibernateTemplate.findByNamedParam(" from Message where id = :id", "id", id);
        return listMsg == null || listMsg.isEmpty() ? new Message() : listMsg.get(0);
    }

    @Override
    public List<Message> getAll() {
        return hibernateTemplate.find("from Message order by id_user");
    }

    @Override
    public void add(Message message) {
        hibernateTemplate.save(message);
        hibernateTemplate.flush();
    }

    @Override
    public void update(Message message) {
        hibernateTemplate.saveOrUpdate(message);
        hibernateTemplate.flush();
    }

    @Override
    public void delete(Message message) {
        hibernateTemplate.delete(message);
        hibernateTemplate.flush();
    }

}
