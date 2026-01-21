package com.shimita.crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.shimita.crud.model.User;
import com.shimita.crud.service.UserService;

// ✅ Response wrapper for Android Retrofit compatibility
class ApiResponse {
    private boolean success;
    private String message;

    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    // ✅ Registration endpoint
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User userModel) {
        Optional<User> existingUser = userService.getUserObjectByUserName(userModel.getUsername());

        if (existingUser.isPresent()) {
            return ResponseEntity.ok(new ApiResponse(false, "User already exists"));
        }

        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userService.saveUser(userModel);

        return ResponseEntity.ok(userModel);
    }

    // ✅ Login endpoint
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginRequest) {
        Optional<User> userOptional = userService.getUserObjectByUserName(loginRequest.getUsername());

        if (userOptional.isEmpty()) {
            return (ResponseEntity<?>) ResponseEntity.badRequest();
        }

        User user = userOptional.get();
        boolean passwordMatch = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());

        if (!passwordMatch) {
            return (ResponseEntity<?>) ResponseEntity.notFound();
        }

        return ResponseEntity.ok(user);
    }

    // ✅ Other routes remain unchanged
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/specific/{email}")
    public Optional<User> findUser(@PathVariable String email) {
        return userService.getUserObjectByUserName(email);
    }

    @PutMapping("/update/{email}")
    public ResponseEntity<ApiResponse> updateUser(@RequestBody User userModel, @PathVariable String email) {
        Optional<User> userObject = userService.getUserObjectByUserName(email);

        if (userObject.isEmpty()) {
            return ResponseEntity.ok(new ApiResponse(false, "User not found"));
        }

        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userService.updateUser(userModel, userObject.get().getUserId());
        
        return ResponseEntity.ok(new ApiResponse(true, "User updated successfully"));
    }
}
