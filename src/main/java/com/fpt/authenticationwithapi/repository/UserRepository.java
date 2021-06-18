package com.fpt.authenticationwithapi.repository;

import com.fpt.authenticationwithapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByLogin(String login);
    List<User> findAllByName(String name);
}
