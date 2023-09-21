package by.exLab.demo.service.news;

import by.exLab.demo.core.exceptions.InputSingleDataException;
import by.exLab.demo.enums.ErrorCode;
import by.exLab.demo.repositories.IPictureRepository;
import by.exLab.demo.repositories.entities.newsEntities.Picture;
import by.exLab.demo.service.api.news.IPictureService;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Data
@Service
public class PictureService implements IPictureService {
    @Autowired
    private  IPictureRepository iPictureRepository;
    @Transactional
    public void savePicture(Picture picture) {
        if(picture.getLink() == null) {
            throw new InputSingleDataException("Вы не заполнили все поля значений", ErrorCode.ERROR);
        }
        iPictureRepository.save(picture);
    }
    public Optional<Picture> getPicture(long id) {
        Optional<Picture> picture = iPictureRepository.findById(id);
        if (picture.isEmpty()) {
            throw new InputSingleDataException("Изображения с такимм Id не существует", ErrorCode.ERROR);
        }
        return picture;
    }
    @Transactional
    public void deletePicture(long id) {
        Optional<Picture> picture = iPictureRepository.findById(id);
        if (picture.isEmpty()) {
            throw new InputSingleDataException("Изображения с такимм Id не существует", ErrorCode.ERROR);
        }
        iPictureRepository.deleteById(id);
    }
    @Transactional
    public void editPicture(long id, Picture updatePicture) {
        Optional<Picture> picture = iPictureRepository.findById(id);
        if (picture.isEmpty()) {
            throw new InputSingleDataException("Изображения с такимм Id не существует", ErrorCode.ERROR);
        }
        updatePicture.setPictureId((int) id);
        iPictureRepository.save(updatePicture);
    }


    public List<Picture> getPictureByArticle(long id){

        return iPictureRepository.findPictureByArticle(id);
    }
}
