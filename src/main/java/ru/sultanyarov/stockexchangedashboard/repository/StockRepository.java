package ru.sultanyarov.stockexchangedashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.sultanyarov.stockexchangedashboard.domain.StockDto;

import java.util.UUID;

public interface StockRepository extends JpaRepository<StockDto, UUID> {
    @Query(value = "SELECT cast(stock_id as varchar) FROM stock WHERE ticker = ?1", nativeQuery = true)
    String findByTicker(String ticker);

    void deleteById(UUID stockId);
}
