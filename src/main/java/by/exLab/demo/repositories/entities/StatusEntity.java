package by.exLab.demo.repositories.entities;


import by.exLab.demo.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "barrierfree", name = "status")
public class StatusEntity {
    @Id
    @Enumerated(EnumType.STRING)
    private UserStatus status;
}
