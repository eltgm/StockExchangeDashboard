package ru.sultanyarov.stockexchangedashboard.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Candles {
    private Candle day = new Candle(0d, 0d, 0d, 0d, LocalDate.MAX);
    private List<Candle> week = new ArrayList<>();
    private List<Candle> month = new ArrayList<>();

    public Candles addWeekItem(Candle weekItem) {
        this.week.add(weekItem);
        return this;
    }

    public Candles addMonthItem(Candle monthItem) {
        this.month.add(monthItem);
        return this;
    }
}
