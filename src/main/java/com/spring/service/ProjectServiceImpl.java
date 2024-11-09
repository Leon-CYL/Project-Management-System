package com.spring.service;

import com.spring.model.Chat;
import com.spring.model.Project;
import com.spring.model.User;
import com.spring.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ChatService chatService;


    @Override
    public Project createProject(Project project, User user) throws Exception {
        Project newProject = new Project();

        newProject.setOwner(user);
        newProject.setTags(project.getTags());
        newProject.setName(project.getName());
        newProject.setCategory(project.getCategory());
        newProject.setDescription(project.getDescription());
        newProject.getTeam().add(user);

        //Create Chat
        Chat chat = new Chat();
        chat.setProject(newProject);
        Chat projectChat = chatService.createChat(chat);
        newProject.setChat(projectChat);

        return projectRepository.save(newProject);
    }


    @Override
    public List<Project> getProjectsByTeam(User user, String category, String tag) throws Exception {
        List<Project> projects = projectRepository.findByTeamContainingOrOwner(user, user);

        if (category != null) {
            projects = projects.stream().filter(project -> project.getCategory().equals(category))
                    .toList();
        }

        if (tag != null) {
            projects = projects.stream().filter(project -> project.getTags().contains(tag))
                    .toList();
        }
        return projects;
    }


    @Override
    public Project getProjectById(Long projectId) throws Exception {
        Optional<Project> project = projectRepository.findById(projectId);

        if (project.isEmpty()) {
            throw new Exception("Project not found");
        }

        return project.get();
    }

    @Override
    public void deleteProject(Long projectId, Long userId) throws Exception {
        projectRepository.deleteById(projectId);
    }


    @Override
    public Project updateProject(Long projectId, Project updateProject) throws Exception {
        Project project = getProjectById(projectId);

        project.setTags(updateProject.getTags());
        project.setDescription(updateProject.getDescription());
        project.setName(updateProject.getName());
        project.setCategory(updateProject.getCategory());

        return projectRepository.save(project);
    }


    @Override
    public void addUserToProject(Long projectId, Long userId) throws Exception {
        Project project = getProjectById(projectId);
        User user = userService.findUserById(userId);

        if (project.getTeam().contains(user)) {
            throw new Exception("The User are already in this team!");
        }

        project.getTeam().add(user);
        project.getChat().getUsers().add(user);
        projectRepository.save(project);
    }


    @Override
    public void removeUserFromProject(Long projectId, Long userId) throws Exception {

        Project project = getProjectById(projectId);
        User user = userService.findUserById(userId);

        if (!project.getTeam().contains(user)) {
            throw new Exception("The User are not in this team!");
        }

        project.getTeam().remove(user);
        project.getChat().getUsers().remove(user);
        projectRepository.save(project);
    }


    @Override
    public Chat getChatByProjectId(Long projectId) throws Exception {
        return getProjectById(projectId).getChat();
    }

    @Override
    public List<Project> searchProjects(String keyword, User user) throws Exception {
        return projectRepository.findByNameContainingAndTeamContains(keyword, user);
    }
}
