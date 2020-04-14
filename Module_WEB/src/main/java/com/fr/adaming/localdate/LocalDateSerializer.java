package com.fr.adaming.localdate;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import lombok.extern.slf4j.Slf4j;

/**
 * Serialization de java.time.LocalDateTime "aaaa-MM-jj"
 *
 * @author mbensalha
 * @author Gregoire
 *
 */
@Slf4j

public class LocalDateSerializer extends JsonSerializer<LocalDate> {
    @Override
    public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
    	log.info("LocalDateSerializer : méthode serialize appelée");
    	System.out.println("/n Serializer: " + DateTimeFormatter.ISO_LOCAL_DATE);
        jsonGenerator.writeString(localDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
    }
}
