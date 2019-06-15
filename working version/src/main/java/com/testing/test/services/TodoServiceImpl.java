package com.testing.test.services;

import com.testing.test.models.Todo;
import com.testing.test.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
@Service(value = "todoService")
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todorepos;

    @Override
    public List<Todo> findAll() {
        List<Todo> todoList = new ArrayList<>();
        todorepos.findAll().iterator().forEachRemaining(todoList::add);
        return todoList;
    }

    @Override
    public List<Todo> findByUserName(String username)
    {
        List<Todo> list = new ArrayList<>();
        todorepos.findAll().iterator().forEachRemaining(list::add);

        list.removeIf(q -> !q.getUser().getUsername().equalsIgnoreCase(username));
        return list;
    }

    @Override
    public Todo findTodoById(long todoid) {
        return todorepos.findById(todoid).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Todo save(Todo todo) {
        return todorepos.save(todo);
    }

    @Override
    public void delete(long todoid) {
        if (todorepos.findById(todoid).isPresent()){
            todorepos.deleteById(todoid);
        }else{
            throw new EntityNotFoundException();
        }
    }

    @Override
    public Todo update(Todo todo, long todoid) {
        Todo currentTodo = todorepos.findById(todoid).orElseThrow(EntityNotFoundException::new);
        if (todo.getDatestarted() != null){
            currentTodo.setDatestarted(todo.getDatestarted());
        }
        if (todo.getTodo() != null){
            currentTodo.setTodo(todo.getTodo());
        }
        if (todo.isCompleted()){
            currentTodo.setCompleted(true);
        }else{
            currentTodo.setCompleted(false);
        }
        todorepos.save(currentTodo);
        return currentTodo;
    }
}