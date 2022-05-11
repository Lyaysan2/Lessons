package ru.itis.repositories;

import org.springframework.stereotype.Repository;
import ru.itis.models.Comments;

import java.util.List;

@Repository
public interface CommentsRepository extends CrudRepository<Comments>{
    List<Comments> findByBookId(Integer bookId);
}
