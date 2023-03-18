package ru.sultanyarov.stockexchangedashboard.config.finhub;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriTemplateHandler;

@Configuration
@EnableConfigurationProperties(value = FinhubProperties.class)
public class FinhubApiConfiguration {
    private final FinhubProperties finhubProperties;

    public FinhubApiConfiguration(FinhubProperties finhubProperties) {
        this.finhubProperties = finhubProperties;
    }

    @Bean
    public RestTemplate finhubApi() {
        var defaultUriTemplateHandler = new DefaultUriTemplateHandler();
        defaultUriTemplateHandler.setBaseUrl(finhubProperties.getHost());

        return new RestTemplateBuilder()
                .uriTemplateHandler(defaultUriTemplateHandler)
                .build();
    }
}
