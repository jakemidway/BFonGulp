package by.exLab.demo.core.dto.news;

import by.exLab.demo.core.dto.user.UserDTO;
import by.exLab.demo.validator.api.ValidString;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    @ValidString
    private String comment;
    @ValidString
    private ArticleDTO article;
    @ValidString
    private UserDTO commentator;

}
