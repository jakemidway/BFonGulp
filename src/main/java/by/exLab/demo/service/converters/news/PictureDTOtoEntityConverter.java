package by.exLab.demo.service.converters.news;

import by.exLab.demo.core.dto.news.PictureDTO;
import by.exLab.demo.repositories.entities.newsEntities.Picture;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor

public class PictureDTOtoEntityConverter implements Converter<PictureDTO, Picture> {
    @Override
    public Picture convert(@NonNull PictureDTO pictureDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(pictureDTO, Picture.class);
    }
}
