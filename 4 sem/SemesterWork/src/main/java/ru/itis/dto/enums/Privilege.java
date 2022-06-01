package ru.itis.dto.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Privilege {

    READ("Чтение"),
    WRITE("Запись"),
    CREATE("Создание"),
    DELETE("Удаление"),
    ALL("Все");

    private final String description;
}
