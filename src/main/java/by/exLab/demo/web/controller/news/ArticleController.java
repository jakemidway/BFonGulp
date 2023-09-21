package by.exLab.demo.web.controller.news;

import by.exLab.demo.core.dto.news.ArticleDTO;
import by.exLab.demo.enums.Category;
import by.exLab.demo.repositories.entities.newsEntities.Article;
import by.exLab.demo.service.api.news.IArticleService;
import by.exLab.demo.service.converters.news.ArticleDTOtoEntityConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/news")
public class ArticleController {
    @Autowired
    private  IArticleService articleService;
    @Autowired
    private  ArticleDTOtoEntityConverter articleDTOtoEntityConverter;

    @PostMapping("/article/create")
    public ResponseEntity<HttpStatus> createArticle(@RequestBody ArticleDTO articleDTO) {
        articleService.createArticle(articleDTOtoEntityConverter.convert(articleDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/article/{id}")
    public Optional<Article> getArticle(@PathVariable("id") int id) {
        return articleService.getArticle(id);
    }

    @PostMapping("/article/delete/{id}")
    public ResponseEntity<HttpStatus> deleteArticle(@PathVariable("id") int id) {
        articleService.deleteArticle(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/article/edit/{id}")
    public ResponseEntity<HttpStatus> editArticle(@PathVariable("id") int id, @RequestBody ArticleDTO articleDTO) {
        articleService.editArticle(id, articleDTOtoEntityConverter.convert(articleDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/article/byCategory/{category}")
    public ResponseEntity<List<Article>> getArticleByCategory(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @PathVariable("category") String category) {

        List<Article> list = articleService.getArticleByCategory(Category.valueOf(category),  pageNo,  pageSize);
        return new ResponseEntity<List<Article>>(list, new HttpHeaders(), HttpStatus.OK);
    }
}
