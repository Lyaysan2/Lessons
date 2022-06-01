package ru.itis.dto.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Department {
    DRESSING_ROOM ("Перевязочная"),
    THERAPY ("Терапия"),
    GYNECOLOGY ("Гинекология"),
    UROLOGY ("Урология"),
    PROCTOLOGY ("Проктология"),
    X_RAY_ROOM ("Рентген"),
    CARDIOLOGY ("Кардиология"),
    ANALYZES ("Анализы"),
    PEDIATRICS ("Педиатрия"),
    DENTISTRY ("Стоматология"),
    OBSTETRICS ("Акушерство"),
    ULTRASOUND ("Ультрозввуковая диагностика"),
    GASTROENTEROLOGY ("Гастроэнтерология"),
    SURGERY ("Хирургия"),
    DERMATOLOGY ("Дерматология"),
    NEUROLOGY ("Неврология"),
    OTORHINOLARYNGOLOGY ("Оториноларингология"),
    OPHTHALMOLOGY ("Офтальмология"),
    OSTEOPATHY ("Остеопатия"),
    PSYCHOLOGY ("Психология"),
    ENDOCRINOLOGY ("Эндокринология"),
    MASSAGE ("Массаж"),
    ALLERGOLOGY ("Аллергология");

    private final String description;
}
