package ftn.team23.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import ftn.team23.entities.Interval;

import java.io.IOException;

public class IntervalSerializer extends JsonSerializer<Interval> {
    @Override
    public void serialize(Interval interval, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("startDate", interval.getStartDate());
        jsonGenerator.writeNumberField("endDate", interval.getEndDate());
        jsonGenerator.writeEndObject();
    }
}