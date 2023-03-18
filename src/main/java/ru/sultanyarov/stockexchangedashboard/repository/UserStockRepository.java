package ru.sultanyarov.stockexchangedashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.sultanyarov.stockexchangedashboard.domain.UserStock;

import java.util.UUID;

public interface UserStockRepository extends JpaRepository<UserStock, UserStock> {

    @Transactional
    void deleteByUserIdAndStockId(UUID userId, UUID stockId);
}
