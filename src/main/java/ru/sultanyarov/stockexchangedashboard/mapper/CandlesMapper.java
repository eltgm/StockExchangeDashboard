package ru.sultanyarov.stockexchangedashboard.mapper;

import org.mapstruct.Mapper;
import ru.sultanyarov.stockexchangedashboard.domain.Candle;
import ru.sultanyarov.stockexchangedashboard.domain.Candles;
import ru.sultanyarov.stockexchangedashboard.domain.CandlesDto;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.function.Consumer;

@Mapper
public interface CandlesMapper {
    default Candles candlesToDomain(CandlesDto candlesDtoForDay, CandlesDto candlesDtoForWeek, CandlesDto candlesDtoForMonth) {
        var candles = new Candles();

        if (candlesDtoForDay.getHighPrices().length > 0) {
            candles.setDay(
                    new Candle(candlesDtoForDay.getHighPrices()[0],
                            candlesDtoForDay.getLowPrices()[0],
                            candlesDtoForDay.getOpenPrices()[0],
                            candlesDtoForDay.getClosePrices()[0],
                            LocalDate.ofInstant(Instant.ofEpochSecond(candlesDtoForDay.getTimestamps()[0]), ZoneId.systemDefault())));
        }

        addCandleItem(candlesDtoForWeek, candles::addWeekItem);
        addCandleItem(candlesDtoForMonth, candles::addMonthItem);

        return candles;
    }

    private void addCandleItem(CandlesDto candleItem, Consumer<Candle> itemAdder) {
        int candleElements = candleItem.getTimestamps() == null ? 0 : candleItem.getTimestamps().length;
        for (int i = 0; i < candleElements; i++) {
            Double closePrice = candleItem.getClosePrices()[i];
            Double highPrice = candleItem.getHighPrices()[i];
            Double lowPrice = candleItem.getLowPrices()[i];
            Double openPrice = candleItem.getOpenPrices()[i];
            Long timestamp = candleItem.getTimestamps()[i];

            itemAdder.accept(new Candle(highPrice, lowPrice, openPrice, closePrice, LocalDate.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.systemDefault())));
        }
    }
}
