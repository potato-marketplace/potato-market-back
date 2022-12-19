package com.mini.potatomarket.controller;

import com.mini.potatomarket.dto.ImageFileRequestDto;
import com.mini.potatomarket.dto.ResponseDto;
import com.mini.potatomarket.service.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/s3")
public class AwsS3Controller {
    private final AwsS3Service awsS3Service;
    private static String dirName = "product-img";

    // S3 이미지 업로드(+ DB 저장)
    @PostMapping("/file")
    public ResponseEntity<List<ImageFileRequestDto>> uploadFile(@RequestPart List<MultipartFile> multipartFile) {
        return ResponseEntity.ok().body(awsS3Service.uploadFile(multipartFile, dirName));
    }

    // S3 이미지 삭제
    @DeleteMapping("/file")
    public ResponseEntity<ResponseDto> deleteFile(@RequestParam String fileName) {
        awsS3Service.deleteFile(fileName, dirName);
        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK.value(), "이미지 삭제 완료"));
    }
}
