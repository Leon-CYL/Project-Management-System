package com.spring.service;

import com.spring.model.Message;

import java.util.List;

public interface MessageService {
    Message sendMessage(Long senderId, Long projectId, String content) throws Exception;

    List<Message> getMessagesByProjectId(Long projectId) throws Exception;
}
