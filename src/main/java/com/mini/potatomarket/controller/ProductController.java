package com.mini.potatomarket.controller;


import com.mini.potatomarket.dto.ProductRequestDto;
import com.mini.potatomarket.dto.ProductResponseDto;
import com.mini.potatomarket.dto.ResponseDto;
import com.mini.potatomarket.service.ProductService;
import com.mini.potatomarket.util.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController                 //controller + ResponseBody
@RequiredArgsConstructor        //생성자 주입
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    //게시글 작성
    @PostMapping
    public ResponseEntity<ProductResponseDto> addProduct(@RequestBody ProductRequestDto productRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.ok(productService.addProduct(productRequestDto,userDetails.getUser()));
    }
    //게시글 리스트 출력
    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getProducts(){
        return ResponseEntity.ok(productService.getProducts());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable Long id){

        return ResponseEntity.ok(productService.getProduct(id));
    }
    //게시글 업데이트
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDto productRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.ok(productService.updateProduct(id, productRequestDto,userDetails.getUser()));
    }
    //게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteProduct(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        productService.deleteProduct(id,userDetails.getUser());
        return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(), "삭제 성공"));
    }

}
