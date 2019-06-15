package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findByUsername(String username);

    User findUserById(long userid);

    void delete(long userid);

    User save(User user);

    User update(User user, long userid);
}