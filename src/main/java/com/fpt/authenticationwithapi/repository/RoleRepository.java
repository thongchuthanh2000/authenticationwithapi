package com.fpt.authenticationwithapi.repository;

import com.fpt.authenticationwithapi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByName(String name);
}
