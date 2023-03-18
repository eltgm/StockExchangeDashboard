package ru.sultanyarov.stockexchangedashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sultanyarov.stockexchangedashboard.domain.News;

public interface NewsRepository extends JpaRepository<News, Long> {
}
