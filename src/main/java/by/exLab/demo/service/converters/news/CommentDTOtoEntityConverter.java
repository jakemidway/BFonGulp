package by.exLab.demo.service.converters.news;

import by.exLab.demo.core.dto.news.CommentDTO;
import by.exLab.demo.repositories.entities.newsEntities.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor

public class CommentDTOtoEntityConverter implements Converter<CommentDTO, Comment> {
    public Comment convert(@NonNull CommentDTO commentDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(commentDTO, Comment.class);
    }
}
