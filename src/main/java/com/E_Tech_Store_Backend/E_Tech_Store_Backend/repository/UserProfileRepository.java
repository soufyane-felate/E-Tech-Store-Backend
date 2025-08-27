package com.E_Tech_Store_Backend.E_Tech_Store_Backend.repository;

import com.E_Tech_Store_Backend.E_Tech_Store_Backend.model.User;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {
    UserProfile findByUser(User user);
}
