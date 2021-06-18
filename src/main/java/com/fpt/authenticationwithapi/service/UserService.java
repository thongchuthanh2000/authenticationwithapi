package com.fpt.authenticationwithapi.service;



import com.fpt.authenticationwithapi.model.User;

import java.util.List;

public interface UserService {
    User get(Integer id);
    User getByLogin(String login);
    void save(User user);
    void delete(User user);
    void update(User user);
    List<User> getAll();
    List<User> getByName(String name);
}
