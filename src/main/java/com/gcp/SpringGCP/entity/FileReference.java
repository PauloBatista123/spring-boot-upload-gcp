package com.gcp.SpringGCP.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@EqualsAndHashCode(of = "id")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileReference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    private OffsetDateTime createdAt;

    @Column(name = "name")
    private String name;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "content_length")
    private Long contentLength;

    @Column(name = "uri")
    private String uri;

    @Column(name = "blob_id")
    private String blobId;
}
