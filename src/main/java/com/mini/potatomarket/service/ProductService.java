package com.mini.potatomarket.service;

import com.mini.potatomarket.dto.*;
import com.mini.potatomarket.entity.Comment;
import com.mini.potatomarket.entity.ImageFile;
import com.mini.potatomarket.entity.Product;
import com.mini.potatomarket.entity.User;
import com.mini.potatomarket.repository.ImageFileRepository;
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
    private final ImageFileRepository imageFileRepository;

    List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
    List<CommentResponseDto> childCommentList = new ArrayList<>();

    //게시글 생성하기
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto, User user, List<ImageFileRequestDto> imageRequestDtoList){
        Product product = productRepository.save(new Product(productRequestDto,user));         // 저장소에 입력 받은 데이터 저장 // save()때문에 @Transactional 을 사용하지 않아도 됨
        for (ImageFileRequestDto imageFileRequestDto : imageRequestDtoList) {
            ImageFile imageFile = imageFileRepository.save(new ImageFile(imageFileRequestDto, product));
        }
        return new ProductResponseDto(product);
    }
    //전체 게시글 출력 ( 메인화면 )
    public List<ProductResponseDto> getProducts(){
        List<Product> productList =productRepository.findAllByOrderByModifiedAtDesc();  // 저장소에 모든 데이터를 불러와 리스트에 저장
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();                             // 리스트 값을 dto로 감쌈

        for(Product product: productList){
            for(Comment comment : product.getCommentList()){
                if(comment.getParent()==null){                                                  //부모 댓글이 없을 경우
                    for (Comment childComment : comment.getChildren()){                         //자식 댓글 리스트의 데이터를 childcomment에 저장
                        commentResponseDtoList.add(new CommentResponseDto(childComment));
                    }
                }
                commentResponseDtoList.add(new CommentResponseDto(comment));
            }

            List<ImageFileResponseDto> imageFileResponseDtoList = new ArrayList<>();            //이미지 파일 리스트 출력을 위해 ArrayList 생성
            for (ImageFile imageFile : product.getImageFileList()) {
                imageFileResponseDtoList.add(new ImageFileResponseDto(imageFile));              //반복문을 돌면서 entity를 dto로 변환
            }

            productResponseDtoList.add(new ProductResponseDto(product,commentResponseDtoList, imageFileResponseDtoList));
        }
        return productResponseDtoList;
    }
    //게시글 선택 출력( 상세 페이지 )
    @Transactional
    public ProductResponseDto getProduct(Long id) {

        Product product = productRepository.findById(id).orElseThrow();

        for (Comment comment : product.getCommentList()) {
            if(comment.getParent()==null){                                                  //부모 댓글이 없을 경우
                for (Comment childComment : comment.getChildren()){                         //자식 댓글 리스트의 데이터를 childComment에 저장
                    if (id.equals(childComment.getProduct().getId())) {                     //childComment의 id와 받아온 id가 일치할 경우(선택 게시글 저장)
                        childCommentList.add(new CommentResponseDto(childComment));
                    }
                    commentResponseDtoList.add(new CommentResponseDto(comment));
                }
            }
            commentResponseDtoList.add(new CommentResponseDto(comment));
        }

        List<ImageFileResponseDto> imageFileResponseDtoList = new ArrayList<>();
        for (ImageFile imageFile : product.getImageFileList()) {
            imageFileResponseDtoList.add(new ImageFileResponseDto(imageFile));
        }
        return new ProductResponseDto(product, commentResponseDtoList, imageFileResponseDtoList);
    }


    //게시글 업데이트 ( 수정 페이지 )
    @Transactional
    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto, User user){
        Product product =productRepository.findByIdAndUserId(id,user.getId()).orElseThrow();

        for (Comment comment : product.getCommentList()) {
            commentResponseDtoList.add(new CommentResponseDto(comment));
        }

        product.update(productRequestDto);
        return new ProductResponseDto(product);
    }
    //게시글 삭제 ( 수정 페이지 )
    @Transactional
    public void deleteProduct(Long id,User user){
        Product product = productRepository.findByIdAndUserId(id,user.getId()).orElseThrow();
        productRepository.delete(product);
    }


}