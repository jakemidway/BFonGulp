package by.exLab.demo.web.controller.news;

import by.exLab.demo.core.dto.news.PictureDTO;
import by.exLab.demo.repositories.entities.newsEntities.Picture;
import by.exLab.demo.service.api.news.IPictureService;
import by.exLab.demo.service.converters.news.PictureDTOtoEntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/news")
public class PictureController {
    @Autowired
    private IPictureService pictureService;
    @Autowired
    private PictureDTOtoEntityConverter pictureDTOtoEntityConverter;

    @PostMapping("/picture/create")
    public ResponseEntity<HttpStatus> createPicture(@RequestBody PictureDTO pictureDTO) {
        pictureService.savePicture(pictureDTOtoEntityConverter.convert(pictureDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/picture/{id}")
    public Optional<Picture> getPicture(@PathVariable("id") int id) {
        return pictureService.getPicture(id);
    }

    @PostMapping("/picture/delete/{id}")
    public void deletePicture(@PathVariable("id") int id) {
        pictureService.deletePicture(id);
    }

    @PostMapping("/picture/edit/{id}")
    public ResponseEntity<HttpStatus> editPicture(@PathVariable("id") int id, @RequestBody PictureDTO pictureDTO) {
        pictureService.editPicture(id, pictureDTOtoEntityConverter.convert(pictureDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/picture/byArticle/{id}")
    public List<Picture> getPictureByArticle(@PathVariable("id") int id) {
        return pictureService.getPictureByArticle(id);
    }
}