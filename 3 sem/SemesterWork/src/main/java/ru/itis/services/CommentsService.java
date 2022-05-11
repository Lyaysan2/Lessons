package ru.itis.services;

import ru.itis.dto.CommentsDto;
import ru.itis.dto.UserDto;

import java.util.List;

public interface CommentsService {
    List<CommentsDto> getByBookId(Integer bookId);
    CommentsDto addComments(CommentsDto commentsDto);
    UserDto getUserByEmail (String email);
    List<CommentsDto> getAll();
    UserDto getUserById (Integer id);
}

