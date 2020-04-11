package com.pradipta.awss3photo.datastore;

import com.pradipta.awss3photo.profile.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class FakeUserProdileDataStore {
    private static final List<UserProfile> USER_PROFILES = new ArrayList<>();

    static {
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(), "pradipta", null));
    }

    public List<UserProfile> getUserProfiles () {
        return USER_PROFILES;
    }
}
