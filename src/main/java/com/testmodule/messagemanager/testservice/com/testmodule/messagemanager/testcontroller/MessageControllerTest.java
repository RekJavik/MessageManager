package com.testmodule.messagemanager.testservice.com.testmodule.messagemanager.testcontroller;
import com.messagemanager.controller.MessageController;
import com.messagemanager.domain.Message;
import com.messagemanager.domain.User;
import com.messagemanager.service.MessageService;
import com.messagemanager.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/applicationContextTest.xml")
public class MessageControllerTest {
    @Autowired
    private MessageService msgService;

    @Autowired
    private UserService userService;

    private MockMvc mockMvc;

    private User testUser;
    private static final String strMes = "Unit test message";
    private Message ms;

    @Before
    public void setUp() {
        testUser = new User("___testUser"+new Date().getTime());
        mockMvc = MockMvcBuilders.standaloneSetup(new MessageController(msgService)).build();
    }

    @After
    public void after(){
        userService.delete(ms.getUser());
    }

    @Test
    public void deleteMsgTest() throws Exception{
        ms = new Message(strMes, testUser);
        msgService.add(ms);

        this.mockMvc.perform(delete("/deleteMsg/"+ms.getId_message()).param("id", String.valueOf(ms.getId_message())))
                .andExpect(status().isOk());
    }

    @Test
    public void msgListTest() throws Exception{
        ms = new Message(strMes, testUser);
        msgService.add(ms);

        this.mockMvc.perform(get("/msgList")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id_message").exists());

        msgService.delete(ms);
    }
}
