package by.exLab.demo.repositories;

import by.exLab.demo.repositories.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface IUserRepository extends ListCrudRepository<UserEntity, UUID> {

    Page<UserEntity> findAll(Pageable pageable);

}
