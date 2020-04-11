package com.pradipta.awss3photo.profile;

import com.pradipta.awss3photo.datastore.FakeUserProdileDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserProfileDataAccessService {
    private final FakeUserProdileDataStore fakeUserProdileDataStore;
    @Autowired
    public UserProfileDataAccessService(FakeUserProdileDataStore fakeUserProdileDataStore) {
        this.fakeUserProdileDataStore = fakeUserProdileDataStore;
    }

    List<UserProfile> getUserProfiles() {
        return fakeUserProdileDataStore.getUserProfiles();
    }
}
