package com.messagemanager.dao;

import com.messagemanager.domain.Message;

import java.util.List;

/**
 * @author Dimon
 */
public interface MessageDAO {
    public Message getMessageById(Long id);

    public List<Message> getAll();

    public void add(Message message);

    public void update(Message message);

    public void delete(Message message);
}
