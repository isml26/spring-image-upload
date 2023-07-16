package com.example.awss3.datastore;

import com.example.awss3.profile.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class FakeUserProfileDataStore {
    private static  final List<UserProfile> USER_PROFILES =  new ArrayList<UserProfile>();

    static {
        USER_PROFILES.add(new UserProfile(UUID.fromString("2183a4b8-4b00-44dd-afd4-02d83cd1352e"),"jane",null));
        USER_PROFILES.add(new UserProfile(UUID.fromString("3a5671ce-5d5d-41b9-b92f-f7e7a393dea4"),"john",null));
        USER_PROFILES.add(new UserProfile(UUID.fromString("a64320d4-3bb3-4302-af49-eabc7b33ff71"),"claire",null));
    }

    public List<UserProfile> getUserProfiles(){
        return USER_PROFILES;
    }
}
