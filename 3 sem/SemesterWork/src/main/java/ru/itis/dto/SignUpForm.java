package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpForm {
    private String firstName;
    private String lastName;
    private Integer age;
    private String city;
    private String email;
    private String password;
    private Integer avatarId;
}
