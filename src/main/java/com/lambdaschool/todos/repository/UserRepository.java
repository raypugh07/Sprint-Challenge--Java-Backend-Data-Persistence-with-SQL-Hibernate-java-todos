package com.lambdaschool.todos.repository;

//import com.lambdaschool.authenticatedusers.model.User;
import com.lambdaschool.todos.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
    User findByUsername(String username);
}
