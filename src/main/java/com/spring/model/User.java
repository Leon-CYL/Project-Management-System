package com.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String fullName;
    private String password;
    private String email;

    private int projectSize;

    @OneToMany(mappedBy = "assignee", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Issue> assignIssues = new ArrayList<>();
}
