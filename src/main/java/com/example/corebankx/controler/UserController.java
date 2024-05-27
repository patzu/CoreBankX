package com.example.corebankx.controler;

import com.example.corebankx.exception.UserRegistrationException;
import com.example.corebankx.model.App_User;
import com.example.corebankx.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Endpoint for user registration
    @PostMapping("/api/users/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody App_User appUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // If there are validation errors, return a 400 Bad Request response
            Map<String, String> errors = new HashMap<>();
            bindingResult.getAllErrors().forEach(error -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
            return ResponseEntity.badRequest().body(errors);
        } else {
            try {
                userService.registerUser(appUser);
                return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
            } catch (UserRegistrationException e) {
                // Handle user registration exception
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User registration failed: " + e.getMessage());
            } catch (Exception e) {
                // Handle other unexpected exceptions
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register user");
            }
        }
    }
}
