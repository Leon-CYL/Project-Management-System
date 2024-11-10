package com.spring.service;

import com.spring.model.Comment;
import com.spring.model.Issue;
import com.spring.model.User;
import com.spring.repository.CommentRepository;
import com.spring.repository.IssueRepository;
import com.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public Comment createComment(Long issueId, Long userId, String content) throws Exception {
        Optional<Issue> issue = issueRepository.findById(issueId);
        Optional<User> user = userRepository.findById(userId);

        if (issue.isEmpty()) {
            throw new Exception("Issue not found: " + issueId);
        }

        if (user.isEmpty()) {
            throw new Exception("User not found: " + userId);
        }

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setIssue(issue.get());
        comment.setUser(user.get());

        Comment save = commentRepository.save(comment);
        issue.get().getComments().add(comment);

        return save;
    }


    @Override
    public void deleteComment(Long commentId, Long userId) throws Exception {
        Comment comment = commentRepository.findById(commentId).get();
        User user = userRepository.findById(userId).get();

        if (commentRepository.findById(commentId).isEmpty()) {
            throw new Exception("Comment not found: " + commentId);
        }

        if (userRepository.findById(userId).isEmpty()) {
            throw new Exception("User not found: " + userId);
        }

        if (!comment.getUser().equals(user)) {
            throw new Exception("User not authorized to delete comment");
        }

        commentRepository.delete(comment);
    }


    @Override
    public List<Comment> findCommentsByIssueId(Long issueId) {
        return commentRepository.findByIssueId(issueId);
    }
}
