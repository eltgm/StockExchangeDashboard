package ru.sultanyarov.stockexchangedashboard.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CandlesDto {
    @JsonProperty("c")
    private Double[] closePrices = new Double[0];
    @JsonProperty("h")
    private Double[] highPrices = new Double[0];
    @JsonProperty("l")
    private Double[] lowPrices = new Double[0];
    @JsonProperty("o")
    private Double[] openPrices = new Double[0];
    @JsonProperty("t")
    private Long[] timestamps = new Long[0];
}
