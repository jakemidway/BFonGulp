package by.exLab.demo.service.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class LongToLDTDeserializer implements Converter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String source) {
        Long longValue = Long.decode(source);
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(longValue), ZoneOffset.UTC);
    }
}

