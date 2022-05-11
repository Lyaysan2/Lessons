package ru.itis.services;

import ru.itis.models.FileInfo;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Optional;

public interface FilesService {
    FileInfo upload(String fileName, Long size, String contentType, InputStream fileInputStream);
    void download(Integer fileId, OutputStream responseOutputStream);
    FileInfo getFile(Integer id);
}
