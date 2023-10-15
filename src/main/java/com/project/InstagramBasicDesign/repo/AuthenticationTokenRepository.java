package com.projectRaj.InstagramBasicDesign.repo;

import com.projectRaj.InstagramBasicDesign.models.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AuthenticationTokenRepository extends JpaRepository<AuthenticationToken,Long> {
    @Query(value = "SELECT * FROM authentication_token WHERE token = :token", nativeQuery = true)
    AuthenticationToken getToken(@Param("token") String token);


    @Query(value = "SELECT token FROM authentication_token WHERE fk_user_id = :userId", nativeQuery = true)
    String getTokenByUserId(Long userId);
}
