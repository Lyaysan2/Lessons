package ru.itis.dto;

import lombok.*;
import ru.itis.models.Book;
import ru.itis.models.Comments;

import java.sql.Timestamp;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private Integer id;
    private String name;
    private String author;
    private Integer price;
    private Integer sellerId;

    public static BookDto from(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .price(book.getPrice())
                .sellerId(book.getSellerId())
                .build();
    }
}
