package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itis.models.FileInfo;
import ru.itis.repositories.FilesRepository;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
public class FilesServiceImpl implements FilesService {

    private final FilesRepository filesRepository;

    @Value("${storage.path}")
    private String storagePath;

    @Autowired
    public FilesServiceImpl(FilesRepository filesRepository) {
        this.filesRepository = filesRepository;
    }

    @Override
    public FileInfo upload(String fileName, Long fileSize, String contentType, InputStream fileInputStream) {
        FileInfo fileInfo = FileInfo.builder()
                .originalFileName(fileName)
                .storageFileName(UUID.randomUUID() + "." + contentType.split("/")[1])
                .size(fileSize)
                .type(contentType)
                .build();

        try {
            Files.copy(fileInputStream, Paths.get(storagePath + fileInfo.getStorageFileName()));
            filesRepository.save(fileInfo);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return fileInfo;
    }

    @Override
    public void download(Integer fileId, OutputStream outputStream) {

        Optional<FileInfo> file = filesRepository.findById(fileId);
        try {
            Files.copy(Paths.get(storagePath + file.get().getStorageFileName()), outputStream);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public FileInfo getFile(Integer id) {
        return filesRepository.findById(id).orElseThrow(java.util.NoSuchElementException::new);
    }
}
