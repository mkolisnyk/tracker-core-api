package com.mkolisnyk.tracker.core.service;

import com.mkolisnyk.tracker.core.entities.Project;

public interface ProjectService {
    Project add(Project input);
    Project get(Integer id);
    Project update(Integer id, Project input);
    void delete(Integer id);
    Iterable<Project> getAll();
}
