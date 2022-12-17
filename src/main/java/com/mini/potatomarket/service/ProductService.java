package com.mini.potatomarket.service;

import com.mini.potatomarket.dto.ProductResponseDto;
import com.mini.potatomarket.entity.Product;
import com.mini.potatomarket.entity.User;
import com.mini.potatomarket.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    //게시글 생성하기
    public ProductResponseDto addProduct(ProductResponseDto productResponseDto, User user){
        Product product = productRepository.save(new Product(productResponseDto,user));         // 저장소에 입력 받은 데이터 저장 // save()때문에 @Transactional 을 사용하지 않아도 됨
        /*List<CommentDto> commentDtoList = new ArrayList<>();                            // 댓글을 dto로 감쌈
        for (Comment comment : product.getCommentList()) {
            commentDtoList.add(new CommentDto(comment));
        }*/
        return new ProductResponseDto(product /*,commentDtoList*/);
    }
    //전체 게시글 출력 ( 메인화면 )
    public List<ProductResponseDto> getProducts(){
        List<Product> productList =productRepository.findAllByOrderByModifiedAtDesc();  // 저장소에 모든 데이터를 불러와 리스트에 저장
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();                             // 리스트 값을 dto로 감쌈

        for(Product product: productList){
            List<CommentResponseDto> commentDtoList = new ArrayList<>();
            for(Comment commentParent : product.getParentList()){        // 부모
                commentDtoList.add(new CommentRequestDto(comment));
                for(Comment commentChild : product.getChildList()){      // 자식
                }
            }
            ProductResponseDto productResponseDto = new ProductResponseDto(product,commentDtoList);
            productResponseDtoList.add(productResponseDto);
        }
        return productResponseDtoList;
    }
    //게시글 선택 출력( 상세 페이지 )
    @Transactional
    public ProductResponseDto openMemo(Long id) {

        Product product = productRepository.findById(id).orElseThrow();
        List<CommentResponseDto> commentDtoList = new ArrayList<>();
        for (Comment comment : product.getCommentList()) {
            commentDtoList.add(new CommentRequestDto(comment));
        }
        return new ProductResponseDto(product, commentDtoList);
    }


    //게시글 업데이트 ( 수정 페이지 )
    @Transactional
    public ProductResponseDto updateProduct(Long id, ProductResponseDto productResponseDto, User user){
        Product product =productRepository.findByIdAndUserId(id,user.getUsername()).orElseThrow();

        List<CommentResponseDto> commentDtoList = new ArrayList<>();
        for (Comment comment : product.getCommentList()) {
            commentDtoList.add(new CommentRequestDto(comment));
        }

        product.update(productResponseDto);
        return new ProductResponseDto(product);
    }
    //게시글 삭제 ( 수정 페이지 )
    @Transactional
    public void deleteProduct(Long id,User user){
        Product product = productRepository.findByIdAndUserId(id,user.getUsername()).orElseThrow();
        productRepository.delete(product);
    }


}
