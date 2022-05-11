package ru.itis.dto;

import lombok.*;
import ru.itis.models.User;

@Data
@AllArgsConstructor
@Builder
public class UserDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer avatarId;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .avatarId(user.getAvatarId())
                .build();
    }
}