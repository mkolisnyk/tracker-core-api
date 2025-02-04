package com.mkolisnyk.tracker.core.api;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mkolisnyk.tracker.core.entities.Project;
import com.mkolisnyk.tracker.core.service.ProjectService;

@ExtendWith(MockitoExtension.class)
public class ProjectControllerTest {
    private static final String PROJECT_URL = "/api/v1/project";
    private static final String PROJECT_INDEX_URL = PROJECT_URL + "/1";
    private MockMvc mockMvc;

    @InjectMocks
    private ProjectController projectController;
    @Mock
    private ProjectService projectService;
    private ObjectMapper objectMapper;
    private Project project;
    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(projectController)
                .build();
        project = new Project();
        project.setId(1);
        project.setName("Test Name");
        project.setCode("TN");
    }
    @Test
    public void testAddProjectEndpoint() throws Exception {
        given(projectService.add(project)).willReturn(project);
        MockHttpServletResponse response =
                mockMvc.perform(MockMvcRequestBuilders
                        .post(PROJECT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(project))).andReturn().getResponse();

        Project result = objectMapper.readValue(response.getContentAsString(), Project.class);
        Assertions.assertEquals(project, result);
    }
    @Test
    public void testGetAllProjectsEndpoint() throws Exception {
        given(projectService.getAll()).willReturn(List.of(project));
        MockHttpServletResponse response =
                mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/project/all")).andReturn().getResponse();

        Project[] result = objectMapper.readValue(response.getContentAsString(), Project[].class);
        Assertions.assertEquals(1, result.length);
        Assertions.assertEquals(project, (Project) result[0]);
    }
    @Test
    public void testGetProjectEndpoint() throws Exception {
        given(projectService.get(1)).willReturn(project);
        MockHttpServletResponse response =
                mockMvc.perform(MockMvcRequestBuilders
                        .get(PROJECT_INDEX_URL)).andReturn().getResponse();

        Project result = objectMapper.readValue(response.getContentAsString(), Project.class);
        Assertions.assertEquals(project, result);
    }
    @Test
    public void testGetProjectEndpointShouldThrowAnErrorOnNonExistingId() throws Exception {
        given(projectService.get(1)).willThrow(new NoSuchElementException());
        MockHttpServletResponse response =
                mockMvc.perform(MockMvcRequestBuilders
                        .get(PROJECT_INDEX_URL)).andReturn().getResponse();

        Assertions.assertEquals(404, response.getStatus());
    }
    @Test
    public void testUpdateProjectEndpoint() throws Exception {
        given(projectService.update(1, project)).willReturn(project);
        MockHttpServletResponse response =
                mockMvc.perform(MockMvcRequestBuilders
                        .put(PROJECT_INDEX_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(project))).andReturn().getResponse();

        Project result = objectMapper.readValue(response.getContentAsString(), Project.class);
        Assertions.assertEquals(project, result);
    }
    @Test
    public void testUpdateProjectEndpointShouldThrowErrorForNonExistingId() throws Exception {
        given(projectService.update(1, project)).willThrow(new NoSuchElementException());
        MockHttpServletResponse response =
                mockMvc.perform(MockMvcRequestBuilders
                        .put(PROJECT_INDEX_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(project))).andReturn().getResponse();

        Assertions.assertEquals(404, response.getStatus());
    }
    @Test
    public void testDeleteProjectEndpoint() throws Exception {
        doNothing().when(projectService).delete(1);
        MockHttpServletResponse response =
                mockMvc.perform(MockMvcRequestBuilders
                                .delete(PROJECT_INDEX_URL)).andReturn().getResponse();

        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertEquals("Deleted", response.getContentAsString());
    }
    @Test
    public void testDeleteProjectEndpointShouldThrowErrorForNonExistingId() throws Exception {
        doThrow(new NoSuchElementException()).when(projectService).delete(1);
        MockHttpServletResponse response =
                mockMvc.perform(MockMvcRequestBuilders
                        .delete(PROJECT_INDEX_URL)).andReturn().getResponse();

        Assertions.assertEquals(404, response.getStatus());
    }
}
