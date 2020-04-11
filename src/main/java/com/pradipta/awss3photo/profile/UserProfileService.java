package com.pradipta.awss3photo.profile;

import com.pradipta.awss3photo.buckets.BucketName;
import com.pradipta.awss3photo.filestore.FileStore;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserProfileService {
    private final UserProfileDataAccessService userProfileDataAccessService;
    private final FileStore fileStore;

    @Autowired
    public UserProfileService(UserProfileDataAccessService userProfileDataAccessService, FileStore fileStore) {
        this.userProfileDataAccessService = userProfileDataAccessService;
        this.fileStore = fileStore;
    }
    public List<UserProfile> getAllProfiles(){
        return userProfileDataAccessService.getUserProfiles();
    }

    public void uploadUserProfileImage(String username, MultipartFile file) {
        if (file.isEmpty()) throw new IllegalStateException("FIle is empty");
        if (!Arrays.asList(ContentType.IMAGE_JPEG.getMimeType(), ContentType.IMAGE_PNG.getMimeType()).contains(file.getContentType())) {
            throw new IllegalStateException("Not an image");
        }

        UserProfile userProfile = userProfileDataAccessService.getUserProfiles()
                .stream()
                .filter(userProfile1 -> userProfile1.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElseThrow(()-> new IllegalStateException("User profile not found"));

        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        //Store in S3

        String path = String.format("%s%s", BucketName.PROFILE_IMAGE.getBucketName(), userProfile.getUserProfileId());
        String filename = String.format("%s-%s", file.getName(), UUID.randomUUID());
        try {
            fileStore.save(path, filename, Optional.of(metadata), file.getInputStream());
        } catch (IOException e) {
            throw new IllegalStateException("File upload failed");
        }
    }
}
