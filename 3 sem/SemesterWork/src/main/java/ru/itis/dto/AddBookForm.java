package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddBookForm {
    private Integer id;
    private String name;
    private String author;
    private Integer year;
    private String description;
    private Integer price;
    private Integer sellerId;
    private Integer imageId;
}
