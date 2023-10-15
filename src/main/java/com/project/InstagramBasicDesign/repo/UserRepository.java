package com.projectRaj.InstagramBasicDesign.repo;

import com.projectRaj.InstagramBasicDesign.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByEmail(String newEmail);

    User findByEmail(String email);

}
