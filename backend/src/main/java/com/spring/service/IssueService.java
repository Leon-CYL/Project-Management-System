package com.spring.service;

import com.spring.model.Issue;
import com.spring.model.User;
import com.spring.request.IssueRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IssueService {
    Issue getIssueById(Long id) throws Exception;

    List<Issue> getIssuesByProjectId(Long projectId) throws Exception;

    Issue createIssue(IssueRequest issue, User user) throws Exception;

    void deleteIssue(Long issueId, Long userId) throws Exception;

    Issue addUserToIssue(Long issueId, Long userId) throws Exception;

    Issue updateIssueStatus(Long issueId, String status) throws Exception;
}
