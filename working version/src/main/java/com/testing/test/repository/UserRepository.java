package com.testing.test.repository;

//import com.lambdaschool.authenticatedusers.model.User;
import com.testing.test.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
    User findByUsername(String username);
}
