/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.messagemanager.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.web.util.HtmlUtils;

/**
 * @author Dimon
 */
@Entity
@Table(name = "Message")
public class Message implements Serializable {
    @Id
    @Column(nullable = false, unique = true, name = MessageFieldsKey.ID)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_message;

    public Long getId_message() {
        return id_message;
    }

    public void setId_message(Long id_message) {
        this.id_message = id_message;
    }

    @Column(name = MessageFieldsKey.MESSAGE, length = 500, nullable = false)
    private String message;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    @Cascade(CascadeType.PERSIST)
    private User user;

    public static class MessageFieldsKey {
        public static final String ID = "id_message", MESSAGE = "mes";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = HtmlUtils.htmlUnescape(message);
    }

    public User getUser() {
        return user;
    }

    public Message() {
    }

    public Message(String message, User user) {
        this.message = message;
        this.user = user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message1 = (Message) o;

        if (id_message != null ? !id_message.equals(message1.id_message) : message1.id_message != null) return false;
        if (message != null ? !message.equals(message1.message) : message1.message != null) return false;
        return user != null ? user.equals(message1.user) : message1.user == null;

    }

    @Override
    public int hashCode() {
        int result = id_message != null ? id_message.hashCode() : 0;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
