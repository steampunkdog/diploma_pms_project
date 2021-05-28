package mykyta.anyshchenko.diploma.model.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import mykyta.anyshchenko.diploma.model.enums.DynamicEnum;

import java.io.IOException;

public class DynamicEnumSerializer extends JsonSerializer<DynamicEnum<?>> {
        @Override
        public void serialize(DynamicEnum value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
            jgen.writeString(value.getValue());
        }

    }