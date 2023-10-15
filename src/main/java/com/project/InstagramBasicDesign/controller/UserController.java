package com.projectRaj.InstagramBasicDesign.controller;

import com.projectRaj.InstagramBasicDesign.models.User;
import com.projectRaj.InstagramBasicDesign.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("api/sign-up")
    public String signUp(@RequestBody User user) {
            try {
                 return userService.registerUser(user);
            } catch (Exception e) {
                return "INTERNAL_SERVER_ERROR : User registration failed.";
            }
    }

    @PostMapping("api/sign-in/email/{email}")
    public String signIn(@PathVariable String email) {
        try {
            // Implement user authentication logic, generate and return an authentication token
            String token = userService.authenticateUser(email);
            if (token != null) {
                return "User authenticated. Token: " + token;
            } else {
                return userService.authenticateUser(email);
            }
        } catch (Exception e) {
            return "Authentication failed.";
        }
    }

    @PutMapping("api/update-details")
    public String updateDetails(@RequestParam String email,@RequestParam String token, @RequestBody User userDetals) {
        try {
            // Implement logic to update user details, ensure authentication with the token
            if (userService.isValidToken(token).getToken().equals(token)) {
                userService.updateUserDetails(email,userDetals);
                return "User details updated successfully.";
            } else {
                return "Unauthorized. Please sign in again.";
            }
        } catch (Exception e) {
            return "User details update failed.";
        }
    }
}
