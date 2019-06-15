package com.testing.test.services;

import com.testing.test.models.Todo;

import java.util.List;

public interface TodoService {

    List<Todo> findAll();

    Todo findTodoById(long todoid);

    List<Todo> findByUserName (String username);

    void delete(long todoid);

    Todo save(Todo todo);

    Todo update(Todo todo,long todoid);

}
