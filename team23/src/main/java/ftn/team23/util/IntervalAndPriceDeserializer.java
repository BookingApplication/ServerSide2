package ftn.team23.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import ftn.team23.entities.IntervalAndPrice;

import java.io.IOException;

public class IntervalAndPriceDeserializer extends JsonDeserializer<IntervalAndPrice> {
    @Override
    public IntervalAndPrice deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        Long startDate = node.get("startDate").asLong();
        Long endDate = node.get("endDate").asLong();
        Double price = node.get("price").asDouble();
        return new IntervalAndPrice(startDate, endDate, price);
    }
}