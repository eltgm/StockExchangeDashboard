package ru.sultanyarov.stockexchangedashboard.service.news;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sultanyarov.stockexchangedashboard.domain.News;
import ru.sultanyarov.stockexchangedashboard.repository.NewsRepository;
import ru.sultanyarov.stockexchangedashboard.service.FinhubService;

@Slf4j
@Service
public class NewsService {
    private final FinhubService finhubService;
    private final NewsRepository newsRepository;

    public NewsService(FinhubService finhubService, NewsRepository newsRepository) {
        this.finhubService = finhubService;
        this.newsRepository = newsRepository;
    }

    public Page<News> getAllNews(Pageable pageRequest) {
        log.info("Getting all news from db...");
        return newsRepository.findAll(pageRequest);
    }

    @Transactional
    public void loadAllNews() {
        log.info("Remove all old news...");
        newsRepository.deleteAll();

        newsRepository.saveAll(finhubService.loadNews());
    }
}
