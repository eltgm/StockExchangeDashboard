package ru.sultanyarov.stockexchangedashboard.domain;

import lombok.Value;

import java.time.LocalDate;

@Value
public class Candle {
    Double high;
    Double low;
    Double open;
    Double close;
    LocalDate date;
}
