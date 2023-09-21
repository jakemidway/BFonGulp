package by.exLab.demo.core.dto.news;

import by.exLab.demo.validator.api.ValidString;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PictureDTO {
    @ValidString
    private String link;
    @ValidString
    private ArticleDTO article;
}
