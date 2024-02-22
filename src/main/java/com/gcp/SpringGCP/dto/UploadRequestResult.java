package com.gcp.SpringGCP.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UploadRequestResult {

    private Integer fileReferenceId;
    private String publicUri;

}
