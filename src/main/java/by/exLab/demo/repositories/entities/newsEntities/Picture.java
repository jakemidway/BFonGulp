package by.exLab.demo.repositories.entities.newsEntities;

import by.exLab.demo.repositories.entities.newsEntities.Article;
import by.exLab.demo.validator.api.ValidString;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "barrierfree", name = "pictures")
public class Picture implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "picture_id", nullable = false, updatable = false, unique = true)
    private Integer pictureId;

    @Column(name = "pictureLink", nullable = false, length = 1000)
    @ValidString
    private String link;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", nullable = false)
    @JsonBackReference(value = "article-picture")
    @ValidString
    private Article article;

}
