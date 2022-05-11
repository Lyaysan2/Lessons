package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.dto.CommentsDto;
import ru.itis.dto.UserDto;
import ru.itis.models.Book;
import ru.itis.models.Comments;
import ru.itis.models.User;
import ru.itis.repositories.CommentsRepository;
import ru.itis.repositories.UsersRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentsServiceImpl implements CommentsService {

    private final CommentsRepository commentsRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public CommentsServiceImpl(CommentsRepository commentsRepository, UsersRepository usersRepository) {
        this.commentsRepository = commentsRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public List<CommentsDto> getByBookId(Integer bookId) {
        return commentsRepository.findByBookId(bookId).stream()
                .map(CommentsDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserByEmail(String email) {
        return UserDto.from(usersRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new));
    }

    @Override
    public List<CommentsDto> getAll() {
        return commentsRepository.findAll().stream()
                .map(CommentsDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Integer id) {
        return UserDto.from(usersRepository.findById(id).orElseThrow(IllegalArgumentException::new));
    }

    @Override
    public CommentsDto addComments(CommentsDto commentsDto) {
        Comments comments = Comments.builder()
                .user(User.builder()
                        .id(commentsDto.getUser().getId())
                        .firstName(commentsDto.getUser().getFirstName())
                        .lastName(commentsDto.getUser().getLastName())
                        .email(commentsDto.getUser().getEmail())
                        .avatarId(commentsDto.getUser().getAvatarId())
                        .build())
                .book(Book.builder()
                        .id(commentsDto.getBook().getId())
                        .name(commentsDto.getBook().getName())
                        .author(commentsDto.getBook().getAuthor())
                        .sellerId(commentsDto.getBook().getSellerId())
                        .build())
                .text(commentsDto.getText())
                .createdAt(commentsDto.getCreatedAt())
                .build();
        commentsRepository.save(comments);

        Comments savedComments = commentsRepository.findById(comments.getId()).orElseThrow(IllegalArgumentException::new);
        return CommentsDto.from(savedComments);
    }
}

