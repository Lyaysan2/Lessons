package ru.itis.models;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class FileInfo {
    private Integer id;
    private String originalFileName;
    private String storageFileName;
    private Long size;
    private String type;
}
