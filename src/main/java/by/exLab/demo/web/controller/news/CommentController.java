package by.exLab.demo.web.controller.news;

import by.exLab.demo.core.dto.news.CommentDTO;
import by.exLab.demo.repositories.entities.newsEntities.Comment;
import by.exLab.demo.service.api.news.ICommentService;
import by.exLab.demo.service.converters.news.CommentDTOtoEntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/news")
public class CommentController {
    @Autowired
    ICommentService commentService;
    @Autowired
    CommentDTOtoEntityConverter commentDTOtoEntityConverter;
    @PostMapping("/comment/create")
    public ResponseEntity<HttpStatus> createComment(CommentDTO commentDTO) {
        commentService.createComment(commentDTOtoEntityConverter.convert(commentDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @GetMapping("/comment/{id}")
    public Optional<Comment> getComment(@PathVariable("id") int id) {
        return commentService.getComment(id);
    }
    @PostMapping("/comment/delete/{id}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable("id") int id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @PostMapping("/comment/edit/{id}")
    public ResponseEntity<HttpStatus> editComment(@PathVariable("id") int id, @RequestBody CommentDTO commentDTO) {
        commentService.editComment(id, commentDTOtoEntityConverter.convert(commentDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @GetMapping("/comment/byArticle/{id}")
    public List<Comment> getCommentByArticle(@PathVariable("id") int id) {
        return commentService.getCommentByArticle(id);
    }
}
