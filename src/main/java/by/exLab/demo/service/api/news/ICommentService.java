package by.exLab.demo.service.api.news;

import by.exLab.demo.repositories.entities.newsEntities.Comment;

import java.util.List;
import java.util.Optional;

public interface ICommentService {
    void createComment(Comment comment);
    Optional<Comment> getComment(long id);
    void deleteComment(long id);
    void editComment(long id, Comment updateComment);
    List<Comment> getCommentByArticle(long id);

}
