package ru.itis.repositories;

import ru.itis.models.FileInfo;

import java.util.Optional;

public interface FilesRepository extends CrudRepository<FileInfo>{
    Optional<FileInfo> findByStorageName(String name);
}
