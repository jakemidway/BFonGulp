package by.exLab.demo.core.dto.news;

import by.exLab.demo.enums.Category;
import by.exLab.demo.validator.api.ValidString;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTO {
    @ValidString
    private String title;
    private Category category;
    @ValidString
    private String body;
    private String timestamp;
    private boolean published;
    private List<PictureDTO> pictures;
    private List<CommentDTO> comments;
    @ValidString
    private String author;
    private int counter;
    @ValidString
    private String shortBody;

}
