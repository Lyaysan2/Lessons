package ru.itis.utils.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.mongo.FileMongo;

@Mapper(componentModel = "spring")
public interface FileMapper {

    @Mapping(target = "id", source = "fileMongo.id")
    @Mapping(target = "file", source = "fileMongo.file")
    @Mapping(target = "originalFileName", source = "fileInfoEntity.originalFileName")
    @Mapping(target = "size", source = "fileInfoEntity.size")
    @Mapping(target = "type", source = "fileInfoEntity.type")
    FileResponse toResponse(FileMongo fileMongo, FileInfoEntity fileInfoEntity);
}
