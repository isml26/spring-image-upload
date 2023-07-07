package com.example.awss3.bucket;

public enum BucketName {
    PROFILE_IMAGE("java-storage2631");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
