package ru.sultanyarov.stockexchangedashboard.domain;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
public class StockSearchResult {
    private Long count;
    private List<StockLightweight> result;
}
