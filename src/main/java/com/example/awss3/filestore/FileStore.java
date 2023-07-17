package com.example.awss3.filestore;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class FileStore {
    private final AmazonS3 s3;
    @Autowired
    public  FileStore(AmazonS3 s3) {
        this.s3 = s3;
    }

    public void save(String path,
                     String fileName,
                     Optional<Map<String, String>> optionalMetadata,
                     InputStream inputStream) throws IOException {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(inputStream.available());
        optionalMetadata.ifPresent(map -> {
            if (!map.isEmpty()) {
                map.forEach(metadata::addUserMetadata);
            }
        });

        try {
            s3.putObject(path, fileName, inputStream, metadata);
        } catch (AmazonServiceException e) {
            throw new IllegalStateException("Failed to store file to s3", e);
        }
    }

    public byte[] download(String path, String key){
        try {
            S3Object s3Object =  s3.getObject(path,key);
            return IOUtils.toByteArray(s3Object.getObjectContent());
        }catch (AmazonServiceException | IOException e) {
            throw new IllegalStateException("Failed to download", e);
        }
    }

}