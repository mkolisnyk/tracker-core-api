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
import com.mkolisnyk.tracker.core.repository.ProjectRepository;
import com.mkolisnyk.tracker.core.service.ProjectService;

@RestController
@RequestMapping("api/v1")
public class AppController {
	private static final Logger LOGGER = LogManager.getLogger(AppController.class);

	@Autowired
	private ProjectService projectService;
	
	@GetMapping("/ping")
	ResponseEntity<?> ping() {
		return ResponseEntity.ok().build();
	}


	@PostMapping("/project")
	ResponseEntity<?> addProject(@RequestBody Project request) throws Exception {
		Project response = projectService.add(request);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/project/{id}")
	public @ResponseBody ResponseEntity<?> getProject(@PathVariable("id") Integer id) {
		try {
			Project response = projectService.get(id);
			return ResponseEntity.ok(response);
		} catch (NoSuchElementException nsee) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/project/{id}")
	public @ResponseBody ResponseEntity<?> deleteProject(@PathVariable("id") Integer id) {
		try {
			projectService.delete(id);
			return ResponseEntity.ok("Deleted");
		} catch (NoSuchElementException nsee) {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/project/{id}")
	ResponseEntity<?> updateProject(@PathVariable("id") Integer id, @RequestBody Project request) throws Exception {
		try {
			Project response = projectService.update(id, request);
			return ResponseEntity.ok(response);
		} catch (NoSuchElementException nsee) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/project/all")
	public @ResponseBody Iterable<Project> getAllProjects() {
		return projectService.getAll();
	}
}
