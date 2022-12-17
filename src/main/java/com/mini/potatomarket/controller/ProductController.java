package com.mini.potatomarket.controller;


import com.mini.potatomarket.dto.ProductResponseDto;
import com.mini.potatomarket.dto.ResponseMsgDto;
import com.mini.potatomarket.service.ProductService;
import com.mini.potatomarket.util.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController                 //controller + ResponseBody
@RequiredArgsConstructor        //생성자 주입
@RequestMapping("/api")
public class ProductController {
    private static ProductService productService;
    //게시글 작성
    @PostMapping("/product")
    public ProductResponseDto addProduct(@RequestBody ProductResponseDto productResponseDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return productService.addProduct(productResponseDto,userDetails.getUser());
    }
    //게시글 리스트 출력
    @GetMapping("/products")
    public List<ProductResponseDto> getProducts(){
        return productService.getProducts();
    }
    @GetMapping("/product/{id}")
    public ProductResponseDto openMemo(@PathVariable Long id){
        return productService.openMemo(id);
    }
    //게시글 업데이트
    @PutMapping("/product/{id}")
    public ProductResponseDto updateProduct(@PathVariable Long id, @RequestBody ProductResponseDto productResponseDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return productService.updateProduct(id, productResponseDto,userDetails.getUser());
    }
    //게시글 삭제
    @DeleteMapping("/product/{id}")
    public ResponseMsgDto deleteProduct(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        productService.deleteProduct(id,userDetails.getUser());
        return new ResponseMsgDto(HttpStatus.OK.value(), "삭제 성공");
    }

}
