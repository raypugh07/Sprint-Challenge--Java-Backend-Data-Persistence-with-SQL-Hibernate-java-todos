package com.lambdaschool.todos.services;


import com.lambdaschool.todos.models.Todo;
import com.lambdaschool.todos.models.User;
import com.lambdaschool.todos.models.UserRoles;
import com.lambdaschool.todos.repository.RoleRepository;
import com.lambdaschool.todos.repository.ToDoRepository;
import com.lambdaschool.todos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;


/*Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService
{

    @Autowired
    private UserRepository userrepos;

    @Autowired
    private RoleRepository rolerepos;

    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userrepos.findByUsername(username);
        if (user == null)
        {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthority());
    }

    public User findUserById(long id) throws EntityNotFoundException
    {
        return userrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    public List<User> findAll()
    {
        List<User> list = new ArrayList<>();
        userrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public void delete(long id)
    {
        if (userrepos.findById(id).isPresent())
        {
            userrepos.deleteById(id);
        }
        else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    @Transactional
    @Override
    public User save(User user)
    {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPasswordNoEncrypt(user.getPassword());

        ArrayList<UserRoles> newRoles = new ArrayList<>();
        for (UserRoles ur : user.getUserRoles())
        {
            newRoles.add(new UserRoles(newUser, ur.getRole()));
        }
        newUser.setUserRoles(newRoles);

        for (Todo q : user.getTodos())
        {
            newUser.getTodos().add( new Todo(q.getTodo(),q.getDatestarted(),q.getCompleted(), newUser));
        }

        return userrepos.save(newUser);
    }

    @Transactional
    @Override
    public User update(User user, long id)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userrepos.findByUsername(authentication.getName());

        if (currentUser != null)
        {
            if (id == currentUser.getUserid())
            {
                if (user.getUsername() != null)
                {
                    currentUser.setUsername(user.getUsername());
                }

                if (user.getPassword() != null)
                {
                    currentUser.setPasswordNoEncrypt(user.getPassword());
                }

                if (user.getUserRoles().size() > 0)
                {
                    // with so many relationships happening, I decided to go
                    // with old school queries
                    // delete the old ones
                    rolerepos.deleteUserRolesByUserId(currentUser.getUserid());

                    // add the new ones
                    for (UserRoles ur : user.getUserRoles())
                    {
                        rolerepos.insertUserRoles(id, ur.getRole().getRoleid());
                    }
                }

                if (user.getTodos().size() > 0)
                {
                    for (Todo q : user.getTodos())
                    {
                        currentUser.getTodos().add( new Todo(q.getTodo(),q.getDatestarted(),q.getCompleted(), currentUser));
                    }
                }
                return userrepos.save(currentUser);
            }
            else
            {
                throw new EntityNotFoundException(Long.toString(id) + " Not current user");
            }
        }
        else
        {
            throw new EntityNotFoundException(authentication.getName());
        }

    }
}*/


@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userrepos;

    @Autowired
    private RoleRepository rolerepos;

    @Autowired
    private ToDoRepository todorepos;

    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userrepos.findByUsername(username);
        if (user == null)
        {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthority());
    }

    public User findUserById(long userid) throws EntityNotFoundException
    {
        return userrepos.findById(userid)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(userid)));
    }

    public List<User> findAll()
    {
        List<User> list = new ArrayList<>();
        userrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public User findByUsername(String username) {
        return userrepos.findByUsername(username);
    }

    @Override
    public void delete(long userid)
    {
        if (userrepos.findById(userid).isPresent())
        {
            //todorepos.getAllByID(userid);
            userrepos.deleteById(userid);
        }
        else
        {
            throw new EntityNotFoundException(Long.toString(userid));
        }
    }

    @Transactional
    @Override
    public User save(User user)
    {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPasswordNoEncrypt(user.getPassword());

        ArrayList<UserRoles> newRoles = new ArrayList<>();
        for (UserRoles ur : user.getUserRoles())
        {
            newRoles.add(new UserRoles(newUser, ur.getRole()));
        }
        newUser.setUserRoles(newRoles);


        return userrepos.save(newUser);
    }

    @Transactional
    @Override
    public User update(User user, long userid)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userrepos.findByUsername(authentication.getName());

        if (currentUser != null)
        {
            if (userid == currentUser.getUserid())
            {
                if (user.getUsername() != null)
                {
                    currentUser.setUsername(user.getUsername());
                }

                if (user.getPassword() != null)
                {
                    currentUser.setPasswordNoEncrypt(user.getPassword());
                }

                if (user.getUserRoles().size() > 0)
                {
                    rolerepos.deleteUserRolesByUserId(currentUser.getUserid());
                    for (UserRoles ur : user.getUserRoles())
                    {
                        rolerepos.insertUserRoles(userid, ur.getRole().getRoleid());
                    }
                }

                return userrepos.save(currentUser);
            }
            else
            {
                throw new EntityNotFoundException(Long.toString(userid) + " Not current user");
            }
        }
        else
        {
            throw new EntityNotFoundException(authentication.getName());
        }

    }
}