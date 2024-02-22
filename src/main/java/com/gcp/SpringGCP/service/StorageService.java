package com.gcp.SpringGCP.service;

import com.gcp.SpringGCP.dto.UploadRequestResult;
import com.gcp.SpringGCP.entity.FileReference;
import com.gcp.SpringGCP.repository.FileReferenceRepository;
import com.gcp.SpringGCP.utils.ImageUtils;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class StorageService {

    @Autowired
    private FileReferenceRepository fileReferenceRepository;

    @Value("${gcp.project.id}")
    private String projectId;

    @Value("${gcp.bucket.name}")
    private String bucketName;

    public StorageService(){

    }

    public UploadRequestResult generateUploadUrl(MultipartFile file) throws IOException {
        Storage storage = StorageOptions.newBuilder()
                .setProjectId(projectId).build().getService();


        byte[] fileData = FileUtils.readFileToByteArray(ImageUtils.convertFile(file));

        String blobName = file.getOriginalFilename();
        BlobId blobId = BlobId.of(bucketName, blobName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(file.getContentType()).build();
        Blob blob = storage.create(blobInfo, fileData);

        String baseUrl = String.format("https://storage.googleapis.com/%s/%s", bucketName, blobName);

        FileReference fileReference = this.fileReferenceRepository.save(
                FileReference.builder()
                        .name(file.getOriginalFilename())
                        .contentType(file.getContentType())
                        .contentLength(file.getSize())
                        .uri(baseUrl)
                        .blobId(blob.getGeneratedId())
                        .build()
        );

        return UploadRequestResult.builder().fileReferenceId(fileReference.getId()).publicUri(baseUrl).build();
    }

}
