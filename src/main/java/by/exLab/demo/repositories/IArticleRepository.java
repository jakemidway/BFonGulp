package by.exLab.demo.repositories;

import by.exLab.demo.enums.Category;
import by.exLab.demo.repositories.entities.newsEntities.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IArticleRepository extends CrudRepository<Article, Long> {
    List<Article> getArticleByCategory(Category category, Pageable pageable);

}
