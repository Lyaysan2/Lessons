package ru.itis.repositories;

import ru.itis.models.FileInfo;

import java.util.List;
import java.util.Optional;

public interface FilesRepository{
    Optional<FileInfo> findById(Long id);
    List<FileInfo> findAll();
    FileInfo save(FileInfo fileInfo);
    void delete(Long id);
}
