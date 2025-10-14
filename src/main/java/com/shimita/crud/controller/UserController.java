package com.shimita.crud.controller;

import com.shimita.crud.model.UserModel;
import com.shimita.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class UserController {
    @Autowired
    UserService userService;

    //save new user route
    @PostMapping("save")
    UserModel createUser(@RequestBody UserModel userModel) {
        return userService.saveUser(userModel);
    }

    //get all users route
    @GetMapping("users")
    List<UserModel> getAllUsers(){
        return  userService.getAllUsers();
    }
}
