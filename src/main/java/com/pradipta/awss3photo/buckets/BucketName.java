package com.pradipta.awss3photo.buckets;

public enum BucketName {

    PROFILE_IMAGE("s3-spring-photo");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName () {
        return this.bucketName;
    }
}
