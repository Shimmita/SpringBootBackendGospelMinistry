package com.shimita.crud.service;

import com.shimita.crud.model.UserModel;
import com.shimita.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserModel saveUser(UserModel userModel){
        //check if user email already

        return  userRepository.save(userModel);
    }

    public List<UserModel> getAllUsers(){
        return userRepository.findAll();
    }

}
