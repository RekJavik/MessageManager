package com.testmodule.messagemanager.testservice;

import com.messagemanager.domain.Message;
import com.messagemanager.domain.User;
import com.messagemanager.service.MessageService;
import com.messagemanager.service.UserService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/applicationContextTest.xml")
public class MessageServiceTest {
    @Autowired
    private MessageService msgService;
    @Autowired
    private UserService userService;

    private String userName;
    @Before
    public void init(){
        userName = "___testUser"+new Date().getTime();
    }

    @Test
    public void addUserTest(){
        User testUser = new User(userName);
        userService.add(testUser);

        User getTestUser = userService.getUserByUserName(userName);
        assertEquals(testUser.getName(),getTestUser.getName());
        assertEquals(testUser.getId(),getTestUser.getId());
        assertTrue(testUser.equals(getTestUser));

        userService.delete(testUser);
        assertNull(userService.getUserByUserName(userName));
    }

    @Test
    public void crudMessageTest() {
        User testUser = new User(userName);
        userService.add(testUser);

        String strMes = "Unit test message";
        Message ms = new Message(strMes, testUser);

        msgService.add(ms);
        assertNotNull(msgService.getMessageById(ms.getId_message()));

        ms.setMessage("Another test message");
        msgService.update(ms);
        assertFalse(msgService.getMessageById(ms.getId_message()).getMessage().equals(strMes));

        msgService.delete(ms);
        userService.delete(testUser);
    }
}
