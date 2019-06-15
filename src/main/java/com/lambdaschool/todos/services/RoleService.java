package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Role;

import java.util.List;

public interface RoleService
{
    List<Role> findAll();

    Role findRoleById(long roleid);

    void delete(long roleid);

    Role save(Role role);
}