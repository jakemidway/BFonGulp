package by.exLab.demo.service.api.news;

import by.exLab.demo.repositories.entities.newsEntities.Picture;

import java.util.List;
import java.util.Optional;

public interface IPictureService {
    void savePicture(Picture picture);
    Optional<Picture> getPicture(long id);
    void deletePicture(long id);
    void editPicture(long id, Picture updatePicture);
    List<Picture> getPictureByArticle(long id);
}
