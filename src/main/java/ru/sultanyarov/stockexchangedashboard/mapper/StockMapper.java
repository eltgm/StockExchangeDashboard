package ru.sultanyarov.stockexchangedashboard.mapper;

import org.mapstruct.Mapper;
import ru.sultanyarov.stockexchangedashboard.domain.StockDto;
import ru.sultanyarov.stockexchangedashboard.stock.dto.Stock;
import ru.sultanyarov.stockexchangedashboard.stock.dto.StockLightweight;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface StockMapper {
    default List<StockLightweight> stringStockLightweightToDto(List<String> lightweight) {
        List<StockLightweight> stockLightweights = new ArrayList<>(lightweight.size());
        for (String s : lightweight) {
            StockLightweight stockLightweight = new StockLightweight();
            stockLightweight.setSymbolAndDescription(s);
            stockLightweights.add(stockLightweight);
        }

        return stockLightweights;
    }

    Stock domainStockToDto(ru.sultanyarov.stockexchangedashboard.domain.Stock stock);

    StockDto dtoStockToDomainDto(ru.sultanyarov.stockexchangedashboard.user.dto.Stock stock);

    ru.sultanyarov.stockexchangedashboard.user.dto.Stock domainDtoToDto(StockDto addStockToFavorites);
}
