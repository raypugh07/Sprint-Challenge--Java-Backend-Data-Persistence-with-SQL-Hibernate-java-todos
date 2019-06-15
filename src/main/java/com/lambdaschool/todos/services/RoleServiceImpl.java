package com.lambdaschool.todos.services;


import com.lambdaschool.todos.models.Role;
import com.lambdaschool.todos.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService
{
    @Autowired
    RoleRepository rolerepos;

    @Override
    public List<Role> findAll()
    {
        List<Role> list = new ArrayList<>();
        rolerepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }


    @Override
    public Role findRoleById(long roleid)
    {
        return rolerepos.findById(roleid).orElseThrow(() -> new EntityNotFoundException(Long.toString(roleid)));
    }


    @Override
    public void delete(long roleid)
    {
        rolerepos.findById(roleid).orElseThrow(() -> new EntityNotFoundException(Long.toString(roleid)));
        rolerepos.deleteById(roleid);
    }


    @Transactional
    @Override
    public Role save(Role role)
    {
        return rolerepos.save(role);
    }
}
