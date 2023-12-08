package com.zuzextask.crud.app;

import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {
    private final UserDao userDao;

    public Controller(UserDao userDao) {
        this.userDao = userDao;
    }

    @PostMapping
    public Long createUser(@RequestBody User user){
        return userDao.createUser(user);
    }

    @GetMapping
    public User getUserById(@RequestParam long id){
        return userDao.getUserById(id);
    }

    @PutMapping
    public void editUser(@RequestBody User user){
        userDao.editUser(user);
    }

    @DeleteMapping
    public void deleteUser(@RequestParam long id){
        userDao.deleteUser(id);
    }
}
