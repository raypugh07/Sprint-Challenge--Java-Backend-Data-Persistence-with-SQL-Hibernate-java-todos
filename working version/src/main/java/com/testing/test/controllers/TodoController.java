package com.testing.test.controllers;

import com.testing.test.models.Todo;
import com.testing.test.services.TodoService;
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
public class TodoController
{
   @Autowired
    TodoService todoService;

    @GetMapping(value = "/todos", produces = {"application/json"})
    public ResponseEntity<?> listAllTodos()
    {
        List<Todo> allTodos = todoService.findAll();
        return new ResponseEntity<>(allTodos, HttpStatus.OK);
    }


    @GetMapping(value = "/todo/{todoid}", produces = {"application/json"})
    public ResponseEntity<?> getTodo(@PathVariable Long todoid)
    {
        Todo q = todoService.findTodoById(todoid);
        return new ResponseEntity<>(q, HttpStatus.OK);
    }


    @GetMapping(value = "/username/{userName}", produces = {"application/json"})
    public ResponseEntity<?> findIdByUserName(@PathVariable String userName)
    {
        List<Todo> theQuotes = todoService.findByUserName(userName);
        return new ResponseEntity<>(theQuotes, HttpStatus.OK);
    }



    @PostMapping(value = "/todo")
    public ResponseEntity<?> addNewTodo(@Valid @RequestBody Todo newTodo) throws URISyntaxException
    {
        newTodo = todoService.save(newTodo);

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

    @PutMapping(value = "/todos/todoid/{todoid}", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<?> updateTodo(@PathVariable long todoid, @RequestBody Todo todo){
        return new ResponseEntity<>(todoService.update(todo, todoid), HttpStatus.OK);
    }


    @DeleteMapping("/todo/{todoid}")
    public ResponseEntity<?> deleteTodoById(@PathVariable long todoid)
    {
        todoService.delete(todoid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}