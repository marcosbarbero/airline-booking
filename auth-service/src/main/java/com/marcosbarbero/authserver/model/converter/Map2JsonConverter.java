package com.marcosbarbero.authserver.model.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.Map;

/**
 * @author Marcos Barbero
 */
@Slf4j
@Converter
public class Map2JsonConverter implements AttributeConverter<Map, String> {

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map meta) {
        String additionalInformation = null;
        try {
            additionalInformation = OBJECT_MAPPER.writeValueAsString(meta);
        } catch (JsonProcessingException ex) {
            log.warn("Cannot write value: {}", meta);
        }
        return additionalInformation;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> convertToEntityAttribute(String json) {
        Map<String, Object> additionalInformation = null;
        try {
            additionalInformation = !StringUtils.isEmpty(json) ? OBJECT_MAPPER.readValue(json, Map.class) : null;
        } catch (IOException ex) {
            log.warn("Could not read value: {}", json);
        }
        return additionalInformation;
    }
}
