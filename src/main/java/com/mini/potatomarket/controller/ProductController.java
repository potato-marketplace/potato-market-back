package com.mini.potatomarket.controller;


import com.mini.potatomarket.dto.ProductRequestDto;
import com.mini.potatomarket.dto.ProductResponseDto;
import com.mini.potatomarket.dto.ResponseDto;
import com.mini.potatomarket.dto.ResponseMsgDto;
import com.mini.potatomarket.service.AwsS3Service;
import com.mini.potatomarket.service.ProductService;
import com.mini.potatomarket.util.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller                 //controller + ResponseBody
@RequiredArgsConstructor        //생성자 주입
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    private final AwsS3Service awsS3Service;
    private static String dirName = "product-img";

    //게시글 작성 + S3 이미지 업로드(+ DB저장)
    @PostMapping
    public ResponseEntity<ResponseDto> addProduct(
            @RequestPart(value = "key") ProductRequestDto productRequestDto,
            @RequestPart(value = "multipartFile") List<MultipartFile> multipartFile,
            @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.ok(productService.addProduct(productRequestDto,userDetails.getUser(), awsS3Service.uploadFile(multipartFile, dirName)));
    }
    //게시글 리스트 출력
    @GetMapping
    public List<ProductResponseDto> getProducts(){
        return productService.getProducts();
    }
    @GetMapping("/{id}")
    public ProductResponseDto getProduct(@PathVariable Long id){
        return productService.getProduct(id);
    }
    //게시글 업데이트
    @PutMapping("/{id}")
    public ProductResponseDto updateProduct(@PathVariable Long id, @RequestBody ProductRequestDto productRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return productService.updateProduct(id, productRequestDto,userDetails.getUser());
    }
    //게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseMsgDto deleteProduct(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        productService.deleteProduct(id,userDetails.getUser());
        return new ResponseMsgDto(HttpStatus.OK.value(), "삭제 성공");
    }

}
