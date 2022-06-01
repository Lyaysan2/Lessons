package ru.itis.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.model.CommentEntity;
import ru.itis.repositories.CommentRepository;
import ru.itis.service.CommentService;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public void saveComment(CommentEntity commentEntity) {
        commentRepository.save(commentEntity);
    }
}
