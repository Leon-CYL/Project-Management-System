package com.spring.controller;

import com.spring.model.Comment;
import com.spring.model.User;
import com.spring.request.CommentRequest;
import com.spring.response.MessageResponse;
import com.spring.service.CommentService;
import com.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;


    @PostMapping()
    public ResponseEntity<Comment> createComment(
            @RequestBody CommentRequest request,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);

        Comment comment = commentService.createComment(request.getIssueId(), user.getId(), request.getContent());
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }


    @DeleteMapping("/{commentId}")
    public ResponseEntity<MessageResponse> deleteComment(
           @PathVariable Long commentId,
           @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        commentService.deleteComment(commentId, user.getId());
        return new ResponseEntity<>(new MessageResponse("Comment deleted!"), HttpStatus.OK);
    }


    @GetMapping("/{issueId}")
    public ResponseEntity<List<Comment>> getCommentByIssueId(
            @PathVariable Long issueId
    ) {
        return new ResponseEntity<>(commentService.findCommentsByIssueId(issueId), HttpStatus.OK);
    }
}
