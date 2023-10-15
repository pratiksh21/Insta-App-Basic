package com.projectRaj.InstagramBasicDesign.service;

import com.projectRaj.InstagramBasicDesign.models.AuthenticationToken;
import com.projectRaj.InstagramBasicDesign.repo.AuthenticationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationTokenService {
    @Autowired
    private AuthenticationTokenRepository tokenRepository;

    public String getTokenByUserId(Long userId) {
        return tokenRepository.getTokenByUserId(userId);
    }
}

