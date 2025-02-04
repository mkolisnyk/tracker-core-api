package com.mkolisnyk.tracker.core.service;

import com.mkolisnyk.tracker.core.entities.Project;
import com.mkolisnyk.tracker.core.entities.User;

public interface UserService {
	User add(User input);
	User get(Integer id);
	User update(Integer id, User input);
    void delete(Integer id);
    Iterable<User> getAll();
}
