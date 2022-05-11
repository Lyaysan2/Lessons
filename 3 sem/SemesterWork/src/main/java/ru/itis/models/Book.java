package ru.itis.models;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private Integer id;
    private String name;
    private String author;
    private Integer year;
    private String description;
    private Integer price;
    private Integer sellerId;
    private Integer imageId;
}
