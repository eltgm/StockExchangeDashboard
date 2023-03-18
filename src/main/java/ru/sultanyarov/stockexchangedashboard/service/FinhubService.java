package ru.sultanyarov.stockexchangedashboard.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.sultanyarov.stockexchangedashboard.config.finhub.FinhubProperties;
import ru.sultanyarov.stockexchangedashboard.domain.*;
import ru.sultanyarov.stockexchangedashboard.mapper.CandlesMapper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class FinhubService {
    private static final String CANDLE_PATH = "stock/candle?symbol={stockSymbol}&resolution=D&from={from}&to={to}&token={token}";

    private final RestTemplate finhubApi;
    private final FinhubProperties finhubProperties;
    private final CandlesMapper candlesMapper;

    public FinhubService(RestTemplate finhubApi, FinhubProperties finhubProperties, CandlesMapper candlesMapper) {
        this.finhubApi = finhubApi;
        this.finhubProperties = finhubProperties;
        this.candlesMapper = candlesMapper;
    }

    public List<News> loadNews() {
        log.info("Trying to get all news from finhub...");
        ResponseEntity<News[]> newsResponseEntity = finhubApi.getForEntity("/news?category=general&token={token}", News[].class, finhubProperties.getApiKey());
        List<News> newsList = Arrays.asList(newsResponseEntity.getBody());

        return newsList;
    }

    public StockSearchResult loadStocksByNamePart(String namePart) {
        ResponseEntity<StockSearchResult> stocksResponseEntity =
                finhubApi.getForEntity("/search?q={namePart}&token={token}", StockSearchResult.class, namePart, finhubProperties.getApiKey());
        return stocksResponseEntity.getBody();
    }

    public Stock loadStockByStockSymbol(String stockSymbol) {
        var now = LocalDateTime.now();
        Instant nowInstant = now.toInstant(ZoneOffset.UTC);
        long timestamp = nowInstant.getEpochSecond();
        var dayAgo = now.minusDays(2);
        var weekAgo = now.minusWeeks(1);
        var threeMonthAgo = now.minusMonths(3);

        Quote quoteForStock = loadQuoteByStockSymbol(stockSymbol);
        ResponseEntity<BasicFinancials> basicFinancialsForStock;
        ResponseEntity<CompanyProfile> companyProfileForStock;
        ResponseEntity<CandlesDto> candlesResponseEntityForDay;
        ResponseEntity<CandlesDto> candlesResponseEntityForWeek;
        ResponseEntity<CandlesDto> candlesResponseEntityForMonth;

        try {
            basicFinancialsForStock = finhubApi.getForEntity("stock/metric?symbol={stockSymbol}&metric=all&token={token}", BasicFinancials.class, stockSymbol, finhubProperties.getApiKey());
        } catch (Exception ex) {
            basicFinancialsForStock = ResponseEntity.ok(new BasicFinancials());
        }
        try {
            companyProfileForStock = finhubApi.getForEntity("stock/profile2?symbol={stockSymbol}&token={token}", CompanyProfile.class, stockSymbol, finhubProperties.getApiKey());
        } catch (Exception ex) {
            companyProfileForStock = ResponseEntity.ok(new CompanyProfile());
        }
        try {
            candlesResponseEntityForDay = finhubApi.getForEntity(CANDLE_PATH,
                    CandlesDto.class, stockSymbol, dayAgo.toInstant(ZoneOffset.UTC).getEpochSecond(), timestamp, finhubProperties.getApiKey());
        } catch (Exception ex) {
            candlesResponseEntityForDay = ResponseEntity.ok(new CandlesDto());
        }
        try {
            candlesResponseEntityForWeek = finhubApi.getForEntity(CANDLE_PATH,
                    CandlesDto.class, stockSymbol, weekAgo.toInstant(ZoneOffset.UTC).getEpochSecond(), timestamp, finhubProperties.getApiKey());
        } catch (Exception ex) {
            candlesResponseEntityForWeek = ResponseEntity.ok(new CandlesDto());
        }
        try {
            candlesResponseEntityForMonth = finhubApi.getForEntity(CANDLE_PATH,
                    CandlesDto.class, stockSymbol, threeMonthAgo.toInstant(ZoneOffset.UTC).getEpochSecond(), timestamp, finhubProperties.getApiKey());
        } catch (Exception ex) {
            candlesResponseEntityForMonth = ResponseEntity.ok(new CandlesDto());
        }

        return Stock.builder()
                .quote(quoteForStock)
                .basicFinancials(basicFinancialsForStock.getBody())
                .profile(companyProfileForStock.getBody())
                .candles(candlesMapper.candlesToDomain(candlesResponseEntityForDay.getBody(), candlesResponseEntityForWeek.getBody(), candlesResponseEntityForMonth.getBody()))
                .build();
    }

    public Quote loadQuoteByStockSymbol(String stockSymbol) {
        try {
            return finhubApi.getForEntity("quote?symbol={stockSymbol}&token={token}", Quote.class, stockSymbol, finhubProperties.getApiKey()).getBody();
        } catch (Exception ex) {
            return new Quote();
        }
    }
}
