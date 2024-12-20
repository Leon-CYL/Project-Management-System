package com.spring.controller;

import com.spring.model.Chat;
import com.spring.model.Invitation;
import com.spring.model.Project;
import com.spring.model.User;
import com.spring.request.InviteRequest;
import com.spring.response.MessageResponse;
import com.spring.service.InvitationService;
import com.spring.service.ProjectService;
import com.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private InvitationService invitationService;


    @GetMapping()
    public ResponseEntity<List<Project>> getProjects(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String tag,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        return new ResponseEntity<>(projectService.getProjectsByTeam(user, category, tag), HttpStatus.OK);
    }


    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProjectsById(
            @PathVariable("projectId") Long projectId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        userService.findUserProfileByJwt(jwt);
        return new ResponseEntity<>(projectService.getProjectById(projectId), HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<Project> createProjects(
            @RequestBody Project project,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        return new ResponseEntity<>(projectService.createProject(project, user), HttpStatus.CREATED);
    }


    @PatchMapping("/{projectId}")
    public ResponseEntity<Project> updateProjects(
            @RequestBody Project project,
            @RequestHeader("Authorization") String jwt,
            @PathVariable("projectId") Long projectId
    ) throws Exception {

        userService.findUserProfileByJwt(jwt);
        return new ResponseEntity<>(projectService.updateProject(projectId, project), HttpStatus.OK);
    }


    @DeleteMapping("/{projectId}")
    public ResponseEntity<MessageResponse> deleteProjects(
            @RequestHeader("Authorization") String jwt,
            @PathVariable("projectId") Long projectId
    ) throws Exception {

        User user = userService.findUserProfileByJwt(jwt);
        projectService.deleteProject(projectId, user.getId());

        MessageResponse response = new MessageResponse("Project deleted successfully!");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/search")
    public ResponseEntity<List<Project>> searchProjects(
            @RequestParam(required = false) String keyword,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {

        User user = userService.findUserProfileByJwt(jwt);
        return new ResponseEntity<>(projectService.searchProjects(keyword, user), HttpStatus.OK);
    }


    @GetMapping("/{projectId}/chat")
    public ResponseEntity<Chat> getChatProjectsById(
            @PathVariable("projectId") Long projectId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {

        userService.findUserProfileByJwt(jwt);
        return new ResponseEntity<>(projectService.getChatByProjectId(projectId), HttpStatus.OK);
    }


    @PostMapping("/invite")
    public ResponseEntity<MessageResponse> inviteProject(
            @RequestBody InviteRequest req,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        userService.findUserProfileByJwt(jwt);
        invitationService.sendInvitation(req.getEmail(), req.getProjectId());
        return new ResponseEntity<>(new MessageResponse("Invitation sent successfully!"), HttpStatus.OK);
    }


    @GetMapping("/accept_invitation")
    public ResponseEntity<Invitation> acceptInvitationProject(
            @RequestParam String token,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Invitation invitation = invitationService.acceptInvitation(token, user.getId());
        projectService.addUserToProject(invitation.getProjectId(), user.getId());
        return new ResponseEntity<>(invitation, HttpStatus.ACCEPTED);
    }
}
