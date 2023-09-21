package by.exLab.demo.repositories;

import by.exLab.demo.repositories.entities.newsEntities.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findCommentByArticle(long id);
}
