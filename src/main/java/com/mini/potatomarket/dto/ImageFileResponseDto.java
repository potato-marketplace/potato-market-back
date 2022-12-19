package com.mini.potatomarket.dto;

import com.mini.potatomarket.entity.ImageFile;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ImageFileResponseDto {
    private Long id;                // 이미지 파일 id
    private String fileName;        // 이미지 파일명
    private String uploadPath;      // 이미지 파일 경로
    private Long fileSize;          // 이미지 파일 크기
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public ImageFileResponseDto(ImageFile imageFile) {
        this.id = imageFile.getId();
        this.fileName = imageFile.getFileName();
        this.uploadPath = imageFile.getUploadPath();
        this.fileSize = imageFile.getFileSize();
        this.createdAt = imageFile.getCreatedAt();
        this.modifiedAt = imageFile.getModifiedAt();
    }
}

