package com.spring.controller;

import com.spring.DTO.IssueDTO;
import com.spring.model.Issue;
import com.spring.model.User;
import com.spring.request.IssueRequest;
import com.spring.response.MessageResponse;
import com.spring.service.IssueService;
import com.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
public class IssueController {
    @Autowired
    private IssueService issueService;

    @Autowired
    private UserService userService;


    @GetMapping("/{issueId}")
    public ResponseEntity<Issue> getIssue(
            @PathVariable("issueId") Long issueId
    ) throws Exception {
        return new ResponseEntity<>(issueService.getIssueById(issueId), HttpStatus.OK);
    }


    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Issue>> getIssueByProject(
            @PathVariable("projectId") Long projectId
    ) throws Exception {
        return new ResponseEntity<>(issueService.getIssuesByProjectId(projectId), HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<IssueDTO> createIssue(
            @RequestBody IssueRequest issue,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User tokenUser = userService.findUserProfileByJwt(jwt);

        Issue createdIssue = issueService.createIssue(issue, tokenUser);

        IssueDTO issueDTO = new IssueDTO();
        issueDTO.setId(createdIssue.getId());
        issueDTO.setTitle(createdIssue.getTitle());
        issueDTO.setDescription(createdIssue.getDescription());
        issueDTO.setStatus(createdIssue.getStatus());
        issueDTO.setPriority(createdIssue.getPriority());
        issueDTO.setTags(createdIssue.getTags());
        issueDTO.setAssignee(createdIssue.getAssignee());
        issueDTO.setDueDate(createdIssue.getDueDate());
        issueDTO.setProject(createdIssue.getProject());
        issueDTO.setProjectId(createdIssue.getProject().getId());

        return new ResponseEntity<>(issueDTO, HttpStatus.OK);
    }


    @DeleteMapping("/{issueId}")
    public ResponseEntity<MessageResponse> deleteIssue(
            @PathVariable("issueId") Long issueId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        issueService.deleteIssue(issueId, user.getId());

        MessageResponse response = new MessageResponse("Deleted issue!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PutMapping("/{issueId}/assignee/{userId}")
    public ResponseEntity<Issue> addUserToIssue(
            @PathVariable("issueId") Long issueId,
            @PathVariable("userId") Long userId
    ) throws Exception {
        return new ResponseEntity<>(issueService.addUserToIssue(issueId, userId), HttpStatus.OK);
    }


    @PutMapping("/{issueId}/state/{status}")
    public ResponseEntity<Issue> updateIssueState(
            @PathVariable("issueId") Long issueId,
            @PathVariable String status
    ) throws Exception {
        return new ResponseEntity<>(issueService.updateIssueStatus(issueId, status), HttpStatus.OK);
    }
}
