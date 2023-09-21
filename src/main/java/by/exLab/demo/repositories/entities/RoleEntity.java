package by.exLab.demo.repositories.entities;

import by.exLab.demo.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "barrierfree", name = "role")
public class RoleEntity {
    @Id
    @Enumerated(EnumType.STRING)
    private UserRole role;
}
