package com.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fullName;
    private String password;
    private String email;

    private int projectSize;

    @OneToMany(mappedBy = "assignee", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Issue> assignIssues = new ArrayList<>();
}
