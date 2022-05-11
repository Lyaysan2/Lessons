package ru.itis.dto;

import lombok.*;
import ru.itis.models.Comments;

import java.sql.Timestamp;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentsDto {
    private Integer id;
    private UserDto user;
    private BookDto book;
    private String text;
    private Timestamp createdAt;

    public static CommentsDto from(Comments comments) {
        return CommentsDto.builder()
                .id(comments.getId())
                .user(UserDto.from(comments.getUser()))
                .book(BookDto.from(comments.getBook()))
                .text(comments.getText())
                .createdAt(comments.getCreatedAt())
                .build();
    }
}