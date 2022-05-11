package ru.itis.dto;

import jdk.jshell.Snippet;
import lombok.*;
import ru.itis.models.Cart;
import ru.itis.models.Comments;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private Integer id;
    private UserDto user;
    private BookDto book;

    public static CartDto from(Cart cart) {
        return CartDto.builder()
                .id(cart.getId())
                .user(UserDto.from(cart.getUser()))
                .book(BookDto.from(cart.getBook()))
                .build();
    }
}

