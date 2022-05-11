package ru.itis.models;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Teacher {
    private Integer id;
    private String first_name;
    private String last_name;
    private String email;
    private String subject;
    private String password;
    private Integer avatar_id;
}
