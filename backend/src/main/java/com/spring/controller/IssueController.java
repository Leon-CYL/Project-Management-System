package com.spring.controller;

import com.spring.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/issue")
public class IssueController {
    @Autowired
    private IssueService issueService;
}
