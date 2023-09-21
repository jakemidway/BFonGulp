package by.exLab.demo.repositories;

import by.exLab.demo.repositories.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface IPersonalAccountRepository extends CrudRepository<UserEntity, UUID> {

    UserEntity findByMail(String mail);

    boolean existsByMail(String mail);

  //  long deleteByVerificationCode(String code);

//    @Modifying
//    @Query("delete from User user_code where user_code.code=:code")
//    void deleteCode(@Param("code") String code);
}
