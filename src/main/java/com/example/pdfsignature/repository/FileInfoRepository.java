package com.example.pdfsignature.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pdfsignature.entity.FileInfo;

public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {

}
