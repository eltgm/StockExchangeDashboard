package ru.sultanyarov.stockexchangedashboard.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    private CompanyProfile profile;
    private Quote quote;
    private BasicFinancials basicFinancials;
    private Candles candles;
}
