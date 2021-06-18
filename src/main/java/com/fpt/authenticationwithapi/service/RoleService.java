package com.fpt.authenticationwithapi.service;


import com.fpt.authenticationwithapi.model.Role;

public interface RoleService {
    Role get(int id);
    Role getByName(String name);
}
