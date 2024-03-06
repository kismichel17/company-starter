package ru.kismi.companystarter.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import ru.kismi.companystarter.config.properties.CompanyStarterProperties;
import ru.kismi.companystarter.config.properties.YamlPropertySourceFactory;
import ru.kismi.companystarter.service.GetCurrentTimeService;
import ru.kismi.companystarter.service.impl.GetCurrentTimeServiceImpl;

@Slf4j
@AutoConfiguration
@RequiredArgsConstructor
@EnableConfigurationProperties(CompanyStarterProperties.class)
@PropertySource(value = "classpath:company-starter-config.yml", factory = YamlPropertySourceFactory.class)
public class WebConfig {

    private final CompanyStarterProperties properties;

    @Bean
    public ObjectMapper objectMapper() {
        var objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());

        log.info("Starter objectMapper created");

        if (properties.getObjectMapper().writeInstantAsTimestamp()) {
            return objectMapper;
        }

        return objectMapper
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Bean
    @ConditionalOnMissingBean
    public GetCurrentTimeService getPrettyCurrentTimeService() {
        return new GetCurrentTimeServiceImpl(objectMapper());
    }
}