package by.exLab.demo.repositories.entities.newsEntities;

import by.exLab.demo.enums.Category;
import by.exLab.demo.validator.api.ValidString;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "barrierfree", name = "articles")
public class Article  implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "article_id", nullable = false, updatable = false, unique = true)
        private Integer articleId;

        @Column(name = "title", nullable = false, length = 1000)
        @ValidString
        private String title;

        @Column(name = "category", length = 100)
        @Enumerated(EnumType.STRING)
        private Category category;

        @Column(name = "body", nullable = false, length = 10000)
        @ValidString
        private String body;

        @Column(name = "timestamp", nullable = false, length = 50)
        private String timestamp;

        @Column(name = "is_published", nullable = false, updatable = true)
        private boolean published;

        @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
        @JsonManagedReference(value = "article-picture")
        private List<Picture> pictures;

        @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
        private List<Comment> comments;

        @Column(name = "author", length = 100)
        @ValidString
        private String author;

        @Column(name = "counter", length = 100)
        private int counter;

        @Column(name = "shortBody", nullable = false, length = 1000)
        @ValidString
        private String shortBody;
}
