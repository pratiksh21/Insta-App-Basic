package com.projectRaj.InstagramBasicDesign.service;

import com.projectRaj.InstagramBasicDesign.models.AuthenticationToken;
import com.projectRaj.InstagramBasicDesign.models.User;
import com.projectRaj.InstagramBasicDesign.repo.AuthenticationTokenRepository;
import com.projectRaj.InstagramBasicDesign.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationTokenRepository tokenRepository;

    @Autowired
    private AuthenticationTokenRepository authenticationTokenRepository;

    @Autowired
    private AuthenticationTokenService authenticationTokenService;

    @Autowired
    PostService postService;


    public String registerUser(User newUser) {
        // Implement user registration logic, e.g., you can perform validation and save the user to the database
        //check if already exist -> Not allowed : try logging in

        String newEmail = newUser.getEmail();

        User existingUser = userRepository.findFirstByEmail(newEmail);

        if(existingUser != null)
        {
            return "Email already in use. Please login with correct credentials";
        }
        userRepository.save(newUser);
        return "Hello... "+ newUser.getFirstName() +" "+newUser.getLastName() +" !!! Your account has been created. Now you can login !!!😊🤝";
    }

    public String authenticateUser(String email) {
        // Implement user authentication logic, e.g., check if the provided credentials are valid
        // If valid, generate and return an authentication token; otherwise, return null

        User currentUser = userRepository.findFirstByEmail(email);
//        postService.setEmail(currentUser.getEmail());
        if(currentUser.getEmail().equals(email) && authenticationTokenService.getTokenByUserId(currentUser.getUserId())!=null){
            return "No token generated : "+ currentUser.getFirstName()+" "+ currentUser.getLastName()+ " is already signed in.";
        }else {
            // For simplicity, a basic example:
            if (isValidUser(currentUser).getEmail().equals(email)) {
                // Generate a token (you should use a secure token library)
                String token = generateAuthToken(currentUser);
                return token;
            } else {
                return null;
            }
        }
    }

    public AuthenticationToken  isValidToken(String token) {
        // Implement token validation logic, e.g., check if the token is valid and not expired
        // You should verify the token's authenticity using a secure method

        // For simplicity, a basic example:
        return tokenRepository.getToken(token);
    }

    public void updateUserDetails(String email, User newDetails) {
        User currentUser = userRepository.findByEmail(email);
        // Implement logic to update user details, e.g., update the user's information in the database
        currentUser.setFirstName(newDetails.getFirstName());
        currentUser.setLastName(newDetails.getLastName());
        currentUser.setAge(newDetails.getAge());
        currentUser.setEmail(newDetails.getEmail());
        currentUser.setPhoneNumber(newDetails.getPhoneNumber());
        userRepository.save(currentUser);
    }


    private User isValidUser(User user) {
        // Implement your user validation logic, e.g., by checking credentials against the database
        // For this example, assume that the user is valid if they exist in the database
        return userRepository.findByEmail(user.getEmail());
    }

    private String generateAuthToken(User user) {
        // Implement a secure token generation mechanism, e.g., using JWT
        // Return the generated token
        // This example uses a simple UUID-based token, not suitable for a production system
        String token = UUID.randomUUID().toString();
        AuthenticationToken authToken = new AuthenticationToken();
        authToken.setToken(token);
        authToken.setUser(user);
        authToken.setTokenCreationDate(LocalDateTime.now());
        tokenRepository.save(authToken);
        return token;
    }
}

