package com.fpt.authenticationwithapi.service.impl;


import com.fpt.authenticationwithapi.model.Role;
import com.fpt.authenticationwithapi.repository.RoleRepository;
import com.fpt.authenticationwithapi.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role get(int id) {
        return roleRepository.getById(id);
    }

    @Override
    public Role getByName(String name) {
        return roleRepository.findByName(name);
    }
}
