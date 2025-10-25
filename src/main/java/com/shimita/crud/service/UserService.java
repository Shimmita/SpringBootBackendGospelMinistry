package com.shimita.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shimita.crud.model.User;
import com.shimita.crud.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    // save user
    public User saveUser(User userModel){
        return userRepository.save(userModel);
    }

    // load user
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    // get user by email or username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("Username not found"));
    }

    // get user object
    public Optional<User> getUserObjectByUserName (String username){
        return userRepository.findByUsername(username);
    }

    // update usa
    public User updateUser (User user, Long userId){
        User updatedUser=userRepository.getReferenceById(userId);
        updatedUser.setFirst_name(user.getFirst_name());
        updatedUser.setLast_name(user.getLast_name());  
        updatedUser.setPhone(user.getPhone());
        updatedUser.setRole(user.getRole()); 
        updatedUser.setImagePath(user.getImagePath()); 
        updatedUser.setPassword((user.getPassword().isEmpty()||user.getPassword().isBlank())?updatedUser.getPassword():user.getPassword());
        // save the user
        return userRepository.save(updatedUser);
    }

}
