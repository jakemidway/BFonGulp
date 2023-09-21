package by.exLab.demo.service.news;

import by.exLab.demo.core.exceptions.InputSingleDataException;
import by.exLab.demo.enums.ErrorCode;
import by.exLab.demo.repositories.ICommentRepository;
import by.exLab.demo.repositories.entities.newsEntities.Comment;
import by.exLab.demo.service.api.news.ICommentService;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Data
@Service
public class CommentService implements ICommentService {
    @Autowired
    private  ICommentRepository iCommentRepository;
    @Transactional
    public void createComment(Comment comment) {
        if(comment.getComment() == null) {
            throw new InputSingleDataException("Вы не заполнили все поля значений", ErrorCode.ERROR);
        }
        iCommentRepository.save(comment);
    }
    public Optional<Comment> getComment(long id) {
        Optional<Comment> comment = iCommentRepository.findById(id);
        if (comment.isEmpty()) {
            throw new InputSingleDataException("Комментария с данным Id не существует", ErrorCode.ERROR);
        }
        return comment;
    }
    @Transactional
    public void deleteComment(long id) {
        Optional<Comment> comment = iCommentRepository.findById(id);
        if (comment.isEmpty()) {
            throw new InputSingleDataException("Комментария с данным Id не существует", ErrorCode.ERROR);
        }
        iCommentRepository.deleteById(id);
    }
    @Transactional
    public void editComment(long id, Comment updateComment) {
        Optional<Comment> comment = iCommentRepository.findById(id);
        if (comment.isEmpty()) {
            throw new InputSingleDataException("Комментария с данным Id не существует", ErrorCode.ERROR);
        }
        updateComment.setCommentId((int) id);
        iCommentRepository.save(updateComment);
    }

    public List<Comment> getCommentByArticle(long id) {

        return iCommentRepository.findCommentByArticle(id);
    }
}
