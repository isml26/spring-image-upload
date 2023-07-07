package com.example.awss3.filestore;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

@Service
public class FileStore {
    private final AmazonS3 s3;
    @Autowired
    public  FileStore(AmazonS3 s3) {
        this.s3 = s3;
    }

    public void save(String path, String fileName, InputStream inputStream, Optional<Map<String,String>> optionalMetadata){
        ObjectMetadata metadata = new ObjectMetadata();
        optionalMetadata.ifPresent(map->{
            if(!map.isEmpty()){
                map.forEach(metadata::addUserMetadata);
            }
        });
        try{
            s3.putObject(path, fileName, inputStream ,metadata);
        }catch(Exception e){
            new IllegalStateException("Failed to store file to s3",e);
        }
    }
}
