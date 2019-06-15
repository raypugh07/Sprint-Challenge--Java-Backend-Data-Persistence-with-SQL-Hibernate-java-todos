package com.lambdaschool.todos.controllers;


import com.lambdaschool.todos.models.Todo;
import com.lambdaschool.todos.services.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodosController
{
    @Autowired
    ToDoService toDoService;

    @GetMapping(value = "/todos", produces = {"application/json"})
    public ResponseEntity<?> listAllTodos()
    {
        List<Todo> allTodos = toDoService.findAll();
        return new ResponseEntity<>(allTodos, HttpStatus.OK);
    }


    @GetMapping(value = "/todo/{todoid}", produces = {"application/json"})
    public ResponseEntity<?> getTodo(@PathVariable Long todoid)
    {
        Todo q = toDoService.findTodoById(todoid);
        return new ResponseEntity<>(q, HttpStatus.OK);
    }


    @GetMapping(value = "/username/{userName}", produces = {"application/json"})
    public ResponseEntity<?> findIdByUserName(@PathVariable String userName)
    {
        List<Todo> theQuotes = toDoService.findByUserName(userName);
        return new ResponseEntity<>(theQuotes, HttpStatus.OK);
    }



    @PostMapping(value = "/todo")
    public ResponseEntity<?> addNewTodo(@Valid @RequestBody Todo newTodo) throws URISyntaxException
    {
        newTodo = toDoService.save(newTodo);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newQuoteURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{todoid}")
                .buildAndExpand(newTodo.getTodoid())
                .toUri();
        responseHeaders.setLocation(newQuoteURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }


    @DeleteMapping("/todo/{todoid}")
    public ResponseEntity<?> deleteTodoById(@PathVariable long todoid)
    {
        toDoService.delete(todoid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
