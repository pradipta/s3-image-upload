package com.pradipta.awss3photo.profile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserProfile {
    private UUID userProfileId;
    private String username;
    private String userProfileImageLink;    //S3 link

    public Optional<String> getUserProfileImageLink() {
        return Optional.ofNullable(this.userProfileImageLink);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserProfile that = (UserProfile) o;

        return Objects.equals(userProfileId, that.getUserProfileId()) &&
                Objects.equals(username, that.getUsername()) &&
                Objects.equals(userProfileImageLink, that.userProfileImageLink);

    }
}
