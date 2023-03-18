package ru.sultanyarov.stockexchangedashboard.config.scheduler;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.stereotype.Component;

import java.time.Duration;

import static java.time.temporal.ChronoUnit.MILLIS;

@Data
@Component
@ConfigurationProperties(prefix = "delay")
public class SchedulerProperties {

    /**
     * Интервал обновления общих новостей (в миллисекундах, также можно использовать формат java.time.Duration)
     */
    @DurationUnit(MILLIS)
    private Duration news;

    /**
     * Интервал обновления списка акций (в миллисекундах, также можно использовать формат java.time.Duration)
     */
    @DurationUnit(MILLIS)
    private Duration stock;
}
