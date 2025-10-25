package com.shimita.crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shimita.crud.model.User;
import com.shimita.crud.service.UserService;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    @Autowired
    UserService userService;

    // save new user route
    @PostMapping("create")
    User createUser(@RequestBody User userModel) throws Exception {

        Optional<User> userObject = userService.getUserObjectByUserName(userModel.getUsername());

        if (userObject.isPresent()) {
            throw new UnsupportedOperationException("user already registered");
        }

        // bcrypt encoder to encrypt the password
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
        userModel.setPassword(bCryptPasswordEncoder.encode(userModel.getPassword()));
        return userService.saveUser(userModel);
    }

    // get all users route
    @GetMapping("all")
    List<User> getAllUsers() {

        return userService.getAllUsers();
    }

    // get user by email
    @GetMapping("{email}")
    Optional<User> findUser(@PathVariable String email) {

        return userService.getUserObjectByUserName(email);
    }

    // update user
    @PutMapping("update/{email}")
    User updateUser(@RequestBody User userModel, @PathVariable String email) throws Exception {

        Optional<User> userObject = userService.getUserObjectByUserName(email);

        if (userObject.isEmpty()) {
            throw new UnsupportedOperationException("user does not exist!");
        }

        // find the user details by username
        Optional<User> user=userService.getUserObjectByUserName(email);

        // bcrypt encoder to encrypt the password
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
        userModel.setPassword(bCryptPasswordEncoder.encode(userModel.getPassword()));
        return userService.updateUser(userModel, user.get().getUserId());
}

}