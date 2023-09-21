package by.exLab.demo.service.news;

import by.exLab.demo.core.exceptions.InputSingleDataException;
import by.exLab.demo.enums.Category;
import by.exLab.demo.enums.ErrorCode;
import by.exLab.demo.repositories.IArticleRepository;
import by.exLab.demo.repositories.entities.newsEntities.Article;
import by.exLab.demo.service.api.news.IArticleService;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Data
@Service
public class ArticleService implements IArticleService {
    @Autowired
    private  IArticleRepository iArticleRepository;
    @Transactional
    public void createArticle(Article article) {
        if(article.getTitle() == null|| article.getCategory() == null
        || article.getBody() == null || article.getAuthor() == null || article.getShortBody() == null){
            throw new InputSingleDataException("Вы не заполнили все поля значений", ErrorCode.ERROR);
        }
        iArticleRepository.save(article);
    }
    public Optional<Article> getArticle(long id) {
        Optional<Article> article = iArticleRepository.findById(id);
        if (article.isEmpty()) {
        throw new InputSingleDataException("Статьи с этим Id не существует", ErrorCode.ERROR);
        }
        int counter = article.get().getCounter();
        ++counter;
        article.get().setCounter(counter);
        iArticleRepository.save(article.get());
        return iArticleRepository.findById(id);
    }
    @Transactional
    public void deleteArticle(long id) {
        Optional<Article> article = iArticleRepository.findById(id);
        if (article.isEmpty()) {
            throw new InputSingleDataException("Статьи с этим Id не существует", ErrorCode.ERROR);
        }
       iArticleRepository.deleteById(id);
    }
    @Transactional
    public void editArticle(long id, Article updateArticle) {
        Optional<Article> article = iArticleRepository.findById(id);
        if (article.isEmpty()) {
            throw new InputSingleDataException("Статьи с этим Id не существует", ErrorCode.ERROR);
        }
        if(updateArticle.getTitle() == null|| updateArticle.getCategory() == null
                || updateArticle.getBody() == null || updateArticle.getAuthor() == null || updateArticle.getShortBody() == null){
            throw new InputSingleDataException("Вы не заполнили все поля значений", ErrorCode.ERROR);
        }
            updateArticle.setArticleId((int) id);
            iArticleRepository.save(updateArticle);
        }


    public List<Article> getArticleByCategory(Category category, int pageNo, int pageSize) {
        if (category==null) {
            throw new InputSingleDataException("Статьи с этим Category не существует", ErrorCode.ERROR);
        }
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return iArticleRepository.getArticleByCategory(category, pageable);
    }
}
