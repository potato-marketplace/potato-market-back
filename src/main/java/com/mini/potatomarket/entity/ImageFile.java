package com.mini.potatomarket.entity;

import com.mini.potatomarket.dto.ImageFileRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class ImageFile extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                // 이미지 파일 id

    @Column(nullable = false)
    private String fileName;        // 이미지 파일명

    @Column(nullable = false)
    private String uploadPath;      // 이미지 파일 경로

    @Column(nullable = false)
    private long fileSize;          // 이미지 파일 크기

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", nullable = true)
    private Product product;

    public ImageFile(String fileName, String uploadPath, long fileSize) {
        this.fileName = fileName;
        this.uploadPath = uploadPath;
        this.fileSize = fileSize;
    }
    public ImageFile(ImageFileRequestDto imageFileRequestDto, Product product) {
        this.fileName = imageFileRequestDto.getFileName();
        this.uploadPath = imageFileRequestDto.getUploadPath();
        this.fileSize = imageFileRequestDto.getFileSize();
        this.product = product;
    }
}