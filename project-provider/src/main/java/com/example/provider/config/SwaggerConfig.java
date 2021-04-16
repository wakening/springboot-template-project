package com.example.provider.config;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import springfox.documentation.schema.configuration.ObjectMapperConfigured;

/**
 * swagger bug fix:
 * https://github.com/springfox/springfox/issues/2353
 *
 * @author wakening
 * @version springfox-swagger2-3.0.0
 */
@Component
public class SwaggerConfig implements ApplicationListener<ObjectMapperConfigured>, CommandLineRunner {

    private ObjectMapper objectMapper;

    @Override
    public void onApplicationEvent(ObjectMapperConfigured objectMapperConfiguredEvent) {
        objectMapper = objectMapperConfiguredEvent.getObjectMapper().disable(MapperFeature.USE_ANNOTATIONS);
    }

    @Override
    public void run(String... args) {
        objectMapper.enable(MapperFeature.USE_ANNOTATIONS);
    }
}
