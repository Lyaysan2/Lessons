package ru.itis.models;

import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Comments {
    private Integer id;
    private User user;
    private Book book;
    private String text;
    private Timestamp createdAt;
}