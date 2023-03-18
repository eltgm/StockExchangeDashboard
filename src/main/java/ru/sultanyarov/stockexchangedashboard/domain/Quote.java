package ru.sultanyarov.stockexchangedashboard.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Quote {
    @JsonProperty("c")
    private Double currentPrice;
    @JsonProperty("dp")
    private Double percentChange;
    @JsonProperty("d")
    private Double change;
    @JsonProperty("h")
    private Double highPriceOfTheDay;
    @JsonProperty("l")
    private Double lowPriceOfTheDay;
    @JsonProperty("o")
    private Double openPriceOfTheDay;
}
