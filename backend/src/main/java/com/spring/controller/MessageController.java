package com.spring.controller;

import com.spring.model.Chat;
import com.spring.model.Message;
import com.spring.model.User;
import com.spring.request.MessageRequest;
import com.spring.service.MessageService;
import com.spring.service.ProjectService;
import com.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;


    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody MessageRequest message)
            throws Exception {
        User user = userService.findUserById(message.getSenderId());
        Chat chat = projectService.getProjectById(message.getProjectId()).getChat();

        Message send = messageService.sendMessage(
                message.getSenderId(),
                message.getProjectId(),
                message.getContent()
        );

        return new ResponseEntity<>(send, HttpStatus.OK);
    }


    @GetMapping("/chat/{projectId}")
    public ResponseEntity<List<Message>> getMessagesByProjectId(
            @PathVariable("projectId") Long projectId
    ) throws Exception {
        return new ResponseEntity<>(messageService.getMessagesByProjectId(projectId), HttpStatus.OK);
    }
}
