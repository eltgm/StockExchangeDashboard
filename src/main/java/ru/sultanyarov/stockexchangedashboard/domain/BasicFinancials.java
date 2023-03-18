package ru.sultanyarov.stockexchangedashboard.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class BasicFinancials {
    private Double high52Week;
    private Double low52Week;
    private Double currentRatioQuarterly;
    private Double bookValueShareGrowth5Y;
    private Double netProfitMargin5Y;
    private Double averageTradingVolume10Day;
    private Double assetTurnoverAnnual;

    @JsonProperty("metric")
    public void initBasicFinancials(Map<String, String> metrics) {
        this.high52Week = Double.parseDouble(metrics.getOrDefault("52WeekHigh", "0"));
        this.low52Week = Double.parseDouble(metrics.getOrDefault("52WeekLow", "0"));
        this.currentRatioQuarterly = Double.parseDouble(metrics.getOrDefault("currentRatioQuarterly", "0"));
        this.bookValueShareGrowth5Y = Double.parseDouble(metrics.getOrDefault("bookValueShareGrowth5Y", "0"));
        this.netProfitMargin5Y = Double.parseDouble(metrics.getOrDefault("netProfitMargin5Y", "0"));
        this.averageTradingVolume10Day = Double.parseDouble(metrics.getOrDefault("10DayAverageTradingVolume", "0"));
        this.assetTurnoverAnnual = Double.parseDouble(metrics.getOrDefault("assetTurnoverAnnual", "0"));
    }
}
