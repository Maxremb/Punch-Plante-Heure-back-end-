package com.fr.adaming.localdate;

import java.io.IOException;
import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * Deserialization de java.time.LocalDate avec le format "aaaa-MM-jj"
 *
 * @author mbensalha
 * @author Gregoire
 *
 */
public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {
	@Override
	public LocalDate deserialize(JsonParser parser, DeserializationContext context) throws IOException {

		return LocalDate.parse(parser.getText());
	}
}
