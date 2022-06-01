package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.model.AccountEntity;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<AccountEntity, UUID> {

    Optional<AccountEntity> findById(UUID userId);

    Optional<AccountEntity> findByEmail(String email);
}
