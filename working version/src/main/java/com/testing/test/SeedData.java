package com.testing.test;


import com.testing.test.models.Role;
import com.testing.test.models.Todo;
import com.testing.test.models.User;
import com.testing.test.models.UserRoles;
import com.testing.test.repository.RoleRepository;
import com.testing.test.repository.TodoRepository;
import com.testing.test.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    RoleRepository rolerepos;
    UserRepository userrepos;
    TodoRepository todorepos;

    public SeedData(RoleRepository rolerepos, UserRepository userrepos,TodoRepository todorepos)
    {
        this.rolerepos = rolerepos;
        this.userrepos = userrepos;
        this.todorepos=todorepos;
    }

    @Override
    public void run(String[] args) throws Exception
    {
        Role r1 = new Role("admin");
        Role r2 = new Role("user");

        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));
        admins.add(new UserRoles(new User(), r2));

        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));

        rolerepos.save(r1);
        rolerepos.save(r2);
        User u1 = new User("barnbarn", "ILuvM4th!", users);
        u1.getTodos().add(new Todo("Finish java-orders-swagger", "2019-01-13 04:04:04",false, u1));
        /*u1.getTodos().add(new Todo("The enemy of my enemy is the enemy I kill last", u1));
        u1.getTodos().add(new Todo("Beam me up", u1));*/

        User u2 = new User("admin", "password", admins);
        u2.getTodos().add(new Todo("Feed the turtles", "2019-03-01 04:04:04",false, u2));
        //u2.getTodos().add(new Todo("The question isn't who is going to let me; it's who is going to stop me.", u2));

        userrepos.save(u1);
        userrepos.save(u2);
    }
}
