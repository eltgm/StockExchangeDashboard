package ru.sultanyarov.stockexchangedashboard.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StockLightweight {
    private String description;
    private String displaySymbol;
    private String symbol;
    private String type;
}
