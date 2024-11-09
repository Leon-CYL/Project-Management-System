package com.spring.service;

import com.spring.model.Invitation;
import jakarta.mail.MessagingException;

public interface InvitationService {
    void sendInvitation(String email, Long projectId) throws MessagingException;

    Invitation acceptInvitation(String token, Long userId) throws Exception;

    String getTokenByUserEmail(String email) throws Exception;

    void deleteToken(String token);
}
