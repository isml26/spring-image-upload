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
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(),"ismail",null));
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(),"john",null));
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(),"claire",null));
    }

    public List<UserProfile> getUserProfiles(){
        return USER_PROFILES;
    }
}
