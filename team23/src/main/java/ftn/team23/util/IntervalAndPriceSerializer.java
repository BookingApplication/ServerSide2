package ftn.team23.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import ftn.team23.entities.IntervalAndPrice;

import java.io.IOException;

public class IntervalAndPriceSerializer extends JsonSerializer<IntervalAndPrice> {
    @Override
    public void serialize(IntervalAndPrice intervalAndPrice, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("startDate", intervalAndPrice.getStartDate());
        jsonGenerator.writeNumberField("endDate", intervalAndPrice.getEndDate());
        jsonGenerator.writeNumberField("price", intervalAndPrice.getPrice());
        jsonGenerator.writeEndObject();
    }
}