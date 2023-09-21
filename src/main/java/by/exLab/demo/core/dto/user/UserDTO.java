package by.exLab.demo.core.dto.user;


import by.exLab.demo.enums.UserRole;
import by.exLab.demo.enums.UserStatus;
import by.exLab.demo.service.converters.LocalDateTimeToLongSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private UUID uuid ;

   // @JsonDeserialize(converter = LongToLDTDeserializer.class)
    @JsonSerialize(using = LocalDateTimeToLongSerializer.class)
    private LocalDateTime dtUpdate;
    private String mail;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String avatarLink;
    private String phoneNumber;
    @NotNull(message = "This ROLE of users doesn't exist")
    private UserRole userRole;
    private UserStatus userStatus;
}
