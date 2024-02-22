package com.gcp.SpringGCP.controller;

import com.gcp.SpringGCP.dto.UploadImageRequest;
import com.gcp.SpringGCP.dto.UploadRequestResult;
import com.gcp.SpringGCP.service.StorageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/uploads")
public class StorageController {

    @Autowired
    private StorageService storageService;

    @PostMapping
    public UploadRequestResult newUploadRequest(@RequestParam("image")MultipartFile file) throws IOException {
        return this.storageService.generateUploadUrl(file);
    }

}
