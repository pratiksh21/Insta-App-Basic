package com.projectRaj.InstagramBasicDesign.service;

import com.projectRaj.InstagramBasicDesign.models.Post;
import com.projectRaj.InstagramBasicDesign.models.User;
import com.projectRaj.InstagramBasicDesign.repo.PostRepository;
import com.projectRaj.InstagramBasicDesign.repo.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public void savePost(Post post) {
        postRepository.save(post);
    }

    public Post getPostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }
}


