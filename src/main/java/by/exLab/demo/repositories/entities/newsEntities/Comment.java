package by.exLab.demo.repositories.entities.newsEntities;

import by.exLab.demo.repositories.entities.UserEntity;
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
@Table(schema = "barrierfree", name = "comments")
public class Comment implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", nullable = false, updatable = false, unique = true)
    private Integer commentId;

    @Column(name = "comment", length = 250)
    @ValidString
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", nullable = false)
    @JsonBackReference(value = "article-comment")
    @ValidString
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference(value = "userEntity-comment")
    @ValidString
    private UserEntity commentator;
}
