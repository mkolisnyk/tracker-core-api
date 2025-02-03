package com.mkolisnyk.tracker.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

import com.mkolisnyk.tracker.core.entities.Project;
import com.mkolisnyk.tracker.core.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	@Override
	public Project add(Project input) {
		return projectRepository.save(input);
	}

	@Override
	public Iterable<Project> getAll() {
		return projectRepository.findAll();
	}

	@Override
	public Project get(Integer id) {
		return projectRepository.findById(id).get();
	}

	@Override
	public void delete(Integer id) {
		projectRepository.deleteById(id);
	}

	@Override
	public Project update(Integer id, Project input) {
		this.get(id).getId();
		return projectRepository.save(input);
	}

}
