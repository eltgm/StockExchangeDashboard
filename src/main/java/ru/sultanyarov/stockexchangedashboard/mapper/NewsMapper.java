package ru.sultanyarov.stockexchangedashboard.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import ru.sultanyarov.stockexchangedashboard.news.dto.News;
import ru.sultanyarov.stockexchangedashboard.news.dto.OneNews;

import java.util.List;

@Mapper
public interface NewsMapper {
    default News pageListNewsToDto(Page<ru.sultanyarov.stockexchangedashboard.domain.News> news) {
        var newsDto = new News();
        newsDto.setTotalPages(news.getTotalPages());
        newsDto.setTotalElements(news.getTotalElements());
        newsDto.setNews(mapNewsList(news.getContent()));

        return newsDto;
    }

    List<OneNews> mapNewsList(List<ru.sultanyarov.stockexchangedashboard.domain.News> news);

    @Mapping(source = "headline", target = "title")
    OneNews newsToOneNews(ru.sultanyarov.stockexchangedashboard.domain.News news);
}
