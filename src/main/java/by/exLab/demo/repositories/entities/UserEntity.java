package by.exLab.demo.repositories.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "barrierfree", name = "user")
//@SecondaryTable(schema = "barrierfree", name = "user_code",
//        pkJoinColumns = @PrimaryKeyJoinColumn(name = "uuid")
//)
public class UserEntity {
    @Id
    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "dt_update")
    @Version
    private LocalDateTime dtUpdate;

    @Column(name = "mail", length = 100)
    private String mail;

    @Column(name = "password", updatable = true, length = 70)
    private String password;

    @Column(name = "first_name",  length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "patronymic", updatable = true, length = 50)
    private String patronymic;

    @Column(name = "avatar_link",updatable = true, length = 200)
    private String avatarLink;

    @Column(name = "phone_number", updatable = true, length = 50)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(schema = "barrierfree", name = "user_role",
            joinColumns=
            @JoinColumn(name="user_uuid"),
            inverseJoinColumns=
            @JoinColumn(name="role_id")
    )
    private RoleEntity roleEntity;

    @Enumerated(EnumType.STRING)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(schema = "barrierfree", name = "user_status",
            joinColumns=
            @JoinColumn(name="user_uuid"),
            inverseJoinColumns=
            @JoinColumn(name="status_id")
    )
    private StatusEntity statusEntity;


}
