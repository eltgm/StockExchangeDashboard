package ru.sultanyarov.stockexchangedashboard.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CompanyProfile {
    private String name;
    private String ticker;
    private String currency;
    private String exchange;
    private String logo;
}
