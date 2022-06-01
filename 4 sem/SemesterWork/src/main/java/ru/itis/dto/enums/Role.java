package ru.itis.dto.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    PATIENT("Пациент"),
    DOCTOR("Врач"),
    ADMIN("Администратор");

    private final String description;
}
