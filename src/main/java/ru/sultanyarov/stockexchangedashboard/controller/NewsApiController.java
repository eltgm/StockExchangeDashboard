package ru.sultanyarov.stockexchangedashboard.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sultanyarov.stockexchangedashboard.mapper.NewsMapper;
import ru.sultanyarov.stockexchangedashboard.news.NewsApi;
import ru.sultanyarov.stockexchangedashboard.news.dto.News;
import ru.sultanyarov.stockexchangedashboard.service.news.NewsService;

import static org.springframework.http.ResponseEntity.ok;

@Validated
@RestController
@RequestMapping("/api/v1")
public class NewsApiController implements NewsApi {
    private final NewsService newsService;
    private final NewsMapper newsMapper;

    public NewsApiController(NewsService newsService, NewsMapper newsMapper) {
        this.newsService = newsService;
        this.newsMapper = newsMapper;
    }

    @Override
    public ResponseEntity<News> getNews(Integer page, Integer pageSize) {
        return ok(newsMapper.pageListNewsToDto(newsService.getAllNews(PageRequest.of(page, pageSize))));
    }
}
