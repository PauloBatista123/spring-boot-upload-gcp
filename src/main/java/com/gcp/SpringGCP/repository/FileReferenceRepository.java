package com.gcp.SpringGCP.repository;

import com.gcp.SpringGCP.entity.FileReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileReferenceRepository extends JpaRepository<FileReference, Integer> {
}
