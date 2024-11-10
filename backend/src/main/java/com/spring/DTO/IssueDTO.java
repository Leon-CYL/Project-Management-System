package com.spring.DTO;

import com.spring.model.Project;
import com.spring.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssueDTO {
    private long id;

    private String title;

    private String description;

    private String status;

    private long projectId;

    private String priority;

    private LocalDate dueDate;

    private List<String> tags = new ArrayList<>();

    private Project project;

    private User assignee;
}
