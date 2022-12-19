package com.mini.potatomarket.service;

import com.mini.potatomarket.dto.CommentRequestDto;
import com.mini.potatomarket.dto.CommentResponseDto;
import com.mini.potatomarket.entity.Comment;
import com.mini.potatomarket.entity.Product;
import com.mini.potatomarket.entity.User;
import com.mini.potatomarket.repository.CommentRepository;
import com.mini.potatomarket.repository.ProductRepository;
import com.mini.potatomarket.repository.UserRepository;
import com.mini.potatomarket.util.error.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.mini.potatomarket.util.error.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public CommentResponseDto createcomment(Long id, Long comment_id, CommentRequestDto requestDto, User user) {
        // 게시글 DB에서 입력받은 id값으로 일치하는 데이터 proudct 객체에 저장
        Product product = productRepository.findById(id).orElseThrow(
                () -> new CustomException(PRODUCT_NOT_FOUND)
        );

        // 댓글 DB에서 입력받은 게시글과 댓글id로 조회해서 일치하는 값 parent_commet 객체에 저장
        Comment comment;
        if (comment_id == 0) {
            comment = commentRepository.save(new Comment(requestDto, user.getNickname(), product, user));
            return new CommentResponseDto(comment, comment_id);
        } else {
            Comment childComment = commentRepository.findById(comment_id).orElseThrow(
                    () -> new CustomException(COMMENT_NOT_FOUND)
            );
            if (commentRepository.findByProductAndId(product, comment_id).equals(null)){
                throw new CustomException(COMMENT_NOT_FOUND);
            }
            comment = commentRepository.save(new Comment(requestDto, user.getNickname(), product, user, childComment));
            return new CommentResponseDto(comment, comment_id);
        }
    }

    @Transactional
    public CommentResponseDto updatecomment(Long id, CommentRequestDto requestDto, User user) {
        // 유저 DB에서 검색한 ID값과 동일한 데이터 comment 객체에 저장
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new CustomException(COMMENT_NOT_FOUND)
        );

        // comment 객체에서 가져온 ID값과 현재 유저의 아이디 값이 같으면 전달받은 데이터 업데이트
        if (comment.getUser().getId().equals(user.getId())) {
            comment.update(requestDto);
        } else {
            throw new CustomException(INVALID_USER);
        }
        return new CommentResponseDto(comment);
    }

    @Transactional
    public CommentResponseDto deletecomment(Long id, User user) {
        // 유저 DB에서 검색한 ID값과 동일한 데이터 comment 객체에 저장
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new CustomException(COMMENT_NOT_FOUND)
        );

        // comment객체의 유저 ID값과 현재 접속한 유저의 ID값이 동일하면 DB에서 ID로 검색한 데이터 삭제
        if (comment.getUser().getId().equals(user.getId())) {
            commentRepository.deleteById(id);
        } else {
            throw new CustomException(INVALID_USER);
        }
        return new CommentResponseDto(comment);
    }
}



