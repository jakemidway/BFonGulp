package by.exLab.demo.service.api.news;

import by.exLab.demo.enums.Category;
import by.exLab.demo.repositories.entities.newsEntities.Article;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IArticleService {
    void createArticle(Article article);
    Optional<Article> getArticle(long id);
    void deleteArticle(long id);
    void editArticle(long id, Article updateArticle);
    List<Article> getArticleByCategory(Category category, int pageNo, int pageSize);
}
