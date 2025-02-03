package com.mkolisnyk.tracker.core.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mkolisnyk.tracker.core.entities.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Integer> {

}
