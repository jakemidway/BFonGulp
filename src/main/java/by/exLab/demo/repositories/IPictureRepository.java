package by.exLab.demo.repositories;

import by.exLab.demo.repositories.entities.newsEntities.Picture;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPictureRepository extends CrudRepository<Picture, Long> {
    List<Picture> findPictureByArticle(long id);
}
