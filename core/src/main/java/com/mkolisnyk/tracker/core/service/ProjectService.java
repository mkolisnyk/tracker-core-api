package com.mkolisnyk.tracker.core.service;

import com.mkolisnyk.tracker.core.entities.Project;

public interface ProjectService {
	Project add(Project input);
	Project get(Integer id);
	Iterable<Project> getAll();
}
