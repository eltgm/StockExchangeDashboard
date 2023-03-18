package ru.sultanyarov.stockexchangedashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sultanyarov.stockexchangedashboard.domain.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUserLogin(String userLogin);
}
