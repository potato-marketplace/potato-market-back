package com.mini.potatomarket.service;

import com.mini.potatomarket.dto.CommentRequestDto;
import com.mini.potatomarket.dto.CommentResponseDto;
import com.mini.potatomarket.entity.Comment;
import com.mini.potatomarket.entity.User;
import com.mini.potatomarket.entity.UserRoleEnum;
import com.mini.potatomarket.repository.CommentRepository;
import com.mini.potatomarket.repository.UserRepository;
import com.mini.potatomarket.util.error.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import static com.mini.potatomarket.util.error.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public CommentResponseDto createcomment(Long id, Long comment_id, CommentRequestDto requestDto, User user) {
        // 게시글 DB에서 입력받은 id값으로 일치하는 데이터 proudct 객체에 저장
        Product product = productRepository.findById(id).orElseThrow(
                () -> new CustomException(PRODUCT_NOT_FOUND)
        );

        // 댓글 DB에서 입력받은 게시글과 댓글id로 조회해서 일치하는 값 parent_commet 객체에 저장
        Comment parent_comment = commentRepository.findByProductAndId(product, comment_id);

        if (parent_comment == null) { //parent_comment 객체에 데이터가 담기지 않았다면
            // 댓글 내용, 유저 닉네임, 포스트, 유저 데이터를 담아서 comment 객체에 저장
            Comment comment = new Comment(requestDto, user.getNickname(), product, user);
            // comment 객채의 depth 필드값은 0으로 설정 -> 부모 댓글이기 때문
            comment.update_depth(0L);
            // comment 객체 DB에 저장
            commentRepository.save(comment);
            return new CommentResponseDto(comment);
        } else {
            // parent_comment 객체에 데이터가 있다면 아래 데이터들을 child_comment 객체에 저장
            // 대댓글이기 때문에 depth값은 1로 저장
            Comment child_comment = Comment.builder()
                    .nickname(user.getNickname())
                    .contents(requestDto.getContent())
                    .depth(1)
                    .user(user)
                    .parent(parent_comment)
                    .build();
            // 댓글 DB에 child_comment 객체 저장
            commentRepository.save(child_comment);
            parent_comment.update_children(child_comment);
            return new CommentResponseDto(child_comment);
        }
    }


    public CommentResponseDto updatecomment(Long id, CommentRequestDto requestDto, User user) {
        // 유저 권한이 뭔지 확인 후 userRoleEnum에 저장
        UserRoleEnum userRoleEnum = user.getRole();
        // 유저 객체 미리 생성
        Comment comment = null;

        // 유저의 권한이 USER라면 comment 객체에 전달받은 댓글 id로 찾은 데이터를 저장
        if (userRoleEnum == UserRoleEnum.USER) {
            comment = commentRepository.findById(id).orElseThrow(
                    () -> new CustomException(COMMENT_NOT_FOUND)
            );
            // comment 객체에서 가져온 ID값과 현재 유저의 아이디 값이 같으면 전달받은 데이터 업데이트
            if (comment.getUser().getId().equals(user.getId())) {
                comment.update(requestDto);
            } else {
                throw new CustomException(INVALID_USER);
            }
        }
        return new CommentResponseDto(comment);
    }

    public CommentResponseDto deletecomment(Long id, User user) {
        // 유저 권한이 확인 후 userRoleEnum 객체에 저장
        UserRoleEnum userRoleEnum = user.getRole();
        // 유저 객체 미리 생성
        Comment comment = null;

        // 유저 권한이 USER라면 comment 객체에 전달받은 댓글 id롤 찾은 데이터를 저장
        if (userRoleEnum == UserRoleEnum.USER) {
            comment = commentRepository.findById(id).orElseThrow(
                    () -> new CustomException(COMMENT_NOT_FOUND)
            );
            // comment객체의 유저 ID값과 현재 접속한 유저의 ID값이 동일하면 DB에서 ID로 검색한 데이터 삭제
            if (comment.getUser().getId().equals(user.getId())) {
                commentRepository.deleteById(id);
            }
        }
        return new CommentResponseDto(comment);
    }
}



