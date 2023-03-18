package ru.sultanyarov.stockexchangedashboard.service.stocks;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sultanyarov.stockexchangedashboard.domain.StockSearchResult;
import ru.sultanyarov.stockexchangedashboard.service.FinhubService;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static org.springframework.util.StringUtils.hasText;

@Slf4j
@Service
public class StockService {
    private final FinhubService finhubService;

    public StockService(FinhubService finhubService) {
        this.finhubService = finhubService;
    }

    public List<String> findStocksByNamePart(String q) {
        if (!hasText(q)) {
            return emptyList();
        }

        log.info("Trying to find stocks by part - {}", q);
        StockSearchResult searchResult = finhubService.loadStocksByNamePart(q);

        return searchResult.getResult()
                .stream()
                .map(stockLightweight ->
                        String.format("%s - %s", stockLightweight.getDisplaySymbol(), stockLightweight.getDescription()))
                .collect(Collectors.toList());
    }
}
