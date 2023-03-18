package ru.sultanyarov.stockexchangedashboard.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sultanyarov.stockexchangedashboard.mapper.StockMapper;
import ru.sultanyarov.stockexchangedashboard.service.FinhubService;
import ru.sultanyarov.stockexchangedashboard.service.stocks.StockService;
import ru.sultanyarov.stockexchangedashboard.stock.StocksApi;
import ru.sultanyarov.stockexchangedashboard.stock.dto.Stock;
import ru.sultanyarov.stockexchangedashboard.stock.dto.StockLightweight;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@Validated
@RestController
@RequestMapping("/api/v1")
public class StocksApiController implements StocksApi {
    private final StockService stockService;
    private final StockMapper stockMapper;
    private final FinhubService finhubService;

    public StocksApiController(StockService stockService, StockMapper stockMapper, FinhubService finhubService) {
        this.stockService = stockService;
        this.stockMapper = stockMapper;
        this.finhubService = finhubService;
    }

    @Override
    public ResponseEntity<List<StockLightweight>> searchStock(String q) {
        return ok(stockMapper.stringStockLightweightToDto(stockService.findStocksByNamePart(q)));
    }

    @Override
    public ResponseEntity<Stock> getStock(String stockId) {
        return ok(stockMapper.domainStockToDto(finhubService.loadStockByStockSymbol(stockId)));
    }
}
