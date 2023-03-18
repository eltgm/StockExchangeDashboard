package ru.sultanyarov.stockexchangedashboard.config.finhub;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotEmpty;

@Data
@ConfigurationProperties(prefix = "finhub")
public class FinhubProperties {
    /**
     * Адрес сервиса
     */
    @NotEmpty
    private String host;

    /**
     * API ключ от сервиса finhub
     */
    @NotEmpty
    private String apiKey;
}
