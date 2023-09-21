package by.exLab.demo.service.converters.news;


import by.exLab.demo.core.dto.news.ArticleDTO;
import by.exLab.demo.repositories.entities.newsEntities.Article;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor

public class ArticleDTOtoEntityConverter implements Converter<ArticleDTO, Article> {
    @Override
    public Article convert(@NonNull ArticleDTO articleDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(articleDTO, Article.class);
    }
}
