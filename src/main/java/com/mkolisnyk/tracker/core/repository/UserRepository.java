package com.mkolisnyk.tracker.core.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mkolisnyk.tracker.core.entities.Project;
import com.mkolisnyk.tracker.core.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

}
