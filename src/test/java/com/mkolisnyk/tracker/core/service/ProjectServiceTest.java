package com.mkolisnyk.tracker.core.service;

import com.mkolisnyk.tracker.core.entities.Project;
import com.mkolisnyk.tracker.core.repository.ProjectRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;
    @InjectMocks
    private ProjectService projectService = new ProjectServiceImpl();

    private Project project;

    @BeforeEach
    public void setup() {
        project = new Project();
        project.setId(1);
        project.setName("Test Name");
        project.setCode("TN");
    }
    @Test
    public void testCreateNewProject() {
        when(projectRepository.save(project)).thenReturn(project);
        Assertions.assertEquals(project, projectService.add(project));
    }
    @Test
    public void testGetProject() {
        when(projectRepository.findById(1)).thenReturn(Optional.of(project));
        Assertions.assertEquals(project, projectService.get(1));
    }

    @Test
    public void testGetAllProjects() {
        when(projectRepository.findAll()).thenReturn(List.of(project));
        List<Project> projects = new ArrayList<>();
        projectService.getAll().forEach(projects::add);
        Assertions.assertEquals(1, projects.size());
        Assertions.assertEquals(project, projects.get(0));
    }

    @Test
    public void testDeleteProject() {
        doNothing().when(projectRepository).deleteById(1);
        projectService.delete(1);
    }
    @Test
    public void testUpdateProject() {
        when(projectRepository.findById(1)).thenReturn(Optional.of(project));
        when(projectRepository.save(project)).thenReturn(project);
        Assertions.assertEquals(project, projectService.update(1, project));
    }
}
