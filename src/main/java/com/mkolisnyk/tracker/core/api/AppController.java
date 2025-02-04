package com.mkolisnyk.tracker.core.api;

import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mkolisnyk.tracker.core.entities.Project;
import com.mkolisnyk.tracker.core.service.ProjectService;

@RestController
@RequestMapping("api/v1")
public class AppController {
    private static final Logger LOGGER = LogManager.getLogger(AppController.class);

    @GetMapping("/ping")
    ResponseEntity<?> ping() {
        return ResponseEntity.ok().build();
    }
}
