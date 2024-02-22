package com.gcp.SpringGCP.dto;

import com.gcp.SpringGCP.entity.FileReference;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UploadImageRequest {

    @NotBlank
    private String fileName;

    @NotBlank
    private String contentType;

    @NotNull
    @Min(1)
    private Long contentLength;

    public FileReference toDomain(){
        return FileReference.builder()
                .contentLength(this.contentLength)
                .contentType(this.contentType)
                .name(this.fileName)
                .build();
    }
}
