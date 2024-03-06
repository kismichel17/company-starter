package ru.kismi.companystarter.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@Data
@ConfigurationProperties(prefix = "company")
public class CompanyStarterProperties {

    @NestedConfigurationProperty
    private ObjectMapperProperties objectMapper;
}