package com.projectRaj.InstagramBasicDesign.controller;

import com.projectRaj.InstagramBasicDesign.models.AuthenticationToken;
import com.projectRaj.InstagramBasicDesign.models.Post;
import com.projectRaj.InstagramBasicDesign.service.PostService;
import com.projectRaj.InstagramBasicDesign.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @PostMapping("api/save/post/token/{token}")
    public String createPost(@RequestBody @Valid Post post, @PathVariable String token) {
        try {
            // Implement logic to save a new post, ensuring user is authenticated
            if (userService.isValidToken(token).getToken().equals(token)) {
                postService.savePost(post);
                return "Post saved successfully.";
            } else {
                return "Unauthorized. Please sign in again.";
            }
        } catch (Exception e) {
            return "Post save failed.";
        }
    }

    @GetMapping("api/post")
    public Object getPost(@RequestParam String token , @RequestParam Long postId){
        try {
            // Implement logic to save a new post, ensuring user is authenticated
            if (userService.isValidToken(token).getToken().equals(token)) {
                return postService.getPostById(postId);
            } else {
                return "Unauthorized. Please sign in again.";
            }
        } catch (Exception e) {
            return "Post retrieval failed.";
        }
    }

}

