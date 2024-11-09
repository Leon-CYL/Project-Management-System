package com.spring.service;

import com.spring.model.Issue;
import com.spring.model.Project;
import com.spring.model.User;
import com.spring.repository.IssueRepository;
import com.spring.repository.ProjectRepository;
import com.spring.request.IssueRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IssueServiceImpl implements IssueService {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Override
    public Issue getIssueById(Long id) throws Exception {
        Optional<Issue> issue = issueRepository.findById(id);

        if (issue.isEmpty()) {
            throw new Exception("Issue not found: " + id);
        }

        return issue.get();
    }

    @Override
    public List<Issue> getIssuesByProjectId(Long projectId) throws Exception {
        return issueRepository.findByProjectId(projectId);
    }

    @Override
    public Issue createIssue(IssueRequest issue, User user) throws Exception {
        Project project = projectService.getProjectById(issue.getProjectId());

        Issue newIssue = new Issue();
        newIssue.setTitle(issue.getTitle());
        newIssue.setDescription(issue.getDescription());
        newIssue.setStatus(issue.getStatus());
        newIssue.setProjectId(issue.getProjectId());
        newIssue.setPriority(issue.getPriority());
        newIssue.setDueDate(issue.getDueDate());
        newIssue.setProject(project);

        return issueRepository.save(newIssue);
    }

    @Override
    public void deleteIssue(Long issueId, Long userId) throws Exception {
        getIssueById(issueId);
        issueRepository.deleteById(issueId);
    }

    @Override
    public Issue addUserToIssue(Long issueId, Long userId) throws Exception {
        User user = userService.findUserById(userId);
        Issue issue = getIssueById(issueId);
        issue.setAssignee(user);
        return issueRepository.save(issue);
    }

    @Override
    public Issue updateIssueStatus(Long issueId, String status) throws Exception {
        Issue issue = getIssueById(issueId);
        issue.setStatus(status);
        return issueRepository.save(issue);
    }
}
