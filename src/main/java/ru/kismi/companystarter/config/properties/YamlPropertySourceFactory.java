package ru.kismi.companystarter.config.properties;

import io.micrometer.common.lang.NonNullApi;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Objects;

@NonNullApi
public class YamlPropertySourceFactory implements PropertySourceFactory {

    @Override
    public PropertySource<?> createPropertySource(@Nullable String name, @NonNull EncodedResource resource) {
        var factory = new YamlPropertiesFactoryBean();
        factory.setResources(resource.getResource());

        var properties = factory.getObject();

        return new PropertiesPropertySource(
                Objects.requireNonNull(resource.getResource().getFilename()),
                Objects.requireNonNull(properties)
        );
    }
}
