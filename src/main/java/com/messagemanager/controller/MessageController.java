/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.messagemanager.controller;

import com.messagemanager.domain.Message;
import com.messagemanager.domain.User;
import com.messagemanager.service.MessageService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Dimon
 */
@Controller
public class MessageController {
    @Autowired
    private MessageService msgService;

    public MessageController() {
    }

    public MessageController(MessageService msgService) {
        this.msgService = msgService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addMessage(@RequestParam(value = "user",required = false) String user,
                             @RequestParam(value = "message",required = false) String message,
                             @RequestParam(value = "id",required = false) Long id,
                             Model model){
        if(id == null && user != null && message != null)
            msgService.add(new Message(message, new User(user)));
        else if(id != null){
            Message editedMessage = msgService.getMessageById(id);
            model.addAttribute("model", editedMessage);
            return "addMsg.jsp";
        }
        return "redirect:/message.jsp";
    }
    
    @RequestMapping(value = "/msgList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Message> view(){
        return msgService.getAll();
    }
    
    @RequestMapping(value = "deleteMsg/{id}", method = RequestMethod.DELETE)
    public @ResponseBody HttpStatus deleteMsg(@PathVariable("id") long id) {
        msgService.delete(msgService.getMessageById(id));
        return msgService.getMessageById(id) == null? HttpStatus.OK:HttpStatus.BAD_REQUEST;
    }  
    
    @RequestMapping(value = "/editMsg", method = RequestMethod.POST, produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE/*POST, produces = MediaType.APPLICATION_JSON_VALUE*/)
    public String editMsg(@RequestParam(value = "id") long id,
                          @RequestParam(value = "message") String message){
        Message updMessage = msgService.getMessageById(id);
        updMessage.setMessage(message);
        msgService.update(updMessage);
        return "redirect:/message.jsp";
    }
}
