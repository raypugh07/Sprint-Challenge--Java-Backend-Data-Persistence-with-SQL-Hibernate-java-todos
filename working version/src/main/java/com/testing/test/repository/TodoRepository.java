package com.testing.test.repository;

import com.testing.test.models.Todo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoRepository extends CrudRepository<Todo,Long> {


    // List<Todo> getAllByID(long todoid);







}
