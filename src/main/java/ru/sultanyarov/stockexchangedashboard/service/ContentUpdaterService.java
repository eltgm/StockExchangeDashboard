package ru.sultanyarov.stockexchangedashboard.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.sultanyarov.stockexchangedashboard.service.news.NewsService;

@Slf4j
@Service
public class ContentUpdaterService {
    private final NewsService newsService;

    public ContentUpdaterService(NewsService newsService) {
        this.newsService = newsService;
    }

    @Scheduled(fixedDelayString = "#{@schedulerProperties.getNews().toMillis()}")
    public void updateNews() {
        log.info("Start updating news...");
        newsService.loadAllNews();
    }
}
