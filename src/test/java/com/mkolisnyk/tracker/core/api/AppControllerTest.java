package com.mkolisnyk.tracker.core.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mkolisnyk.tracker.core.entities.Project;
import com.mkolisnyk.tracker.core.service.ProjectService;
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

import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AppControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private AppController appController;
    @Mock
    private ProjectService projectService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(appController)
                .build();
    }

    @Test
    public void testPingEndpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .head("/api/v1/ping")).andExpect(status().isOk());
    }
}
