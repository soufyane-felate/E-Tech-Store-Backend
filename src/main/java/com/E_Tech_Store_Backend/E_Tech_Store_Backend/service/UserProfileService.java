package com.E_Tech_Store_Backend.E_Tech_Store_Backend.service;

import com.E_Tech_Store_Backend.E_Tech_Store_Backend.model.User;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.model.UserProfile;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    public UserProfile getUserProfileByUser(User user) {
        return userProfileRepository.findByUser(user);
    }

    public UserProfile updateUserProfile(User user, UserProfile updatedProfile) {
        UserProfile existingProfile = userProfileRepository.findByUser(user);
        if (existingProfile != null) {
            existingProfile.setPhone(updatedProfile.getPhone());
            existingProfile.setAddress(updatedProfile.getAddress());
            existingProfile.setCity(updatedProfile.getCity());
            existingProfile.setState(updatedProfile.getState());
            existingProfile.setZipCode(updatedProfile.getZipCode());
            existingProfile.setCountry(updatedProfile.getCountry());
            return userProfileRepository.save(existingProfile);
        }
        return null;
    }

    public void createUserProfile(UserProfile userProfile) {
        userProfileRepository.save(userProfile);
    }
}
