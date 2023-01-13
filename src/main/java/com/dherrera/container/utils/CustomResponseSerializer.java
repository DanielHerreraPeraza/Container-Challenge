package com.dherrera.container.utils;

import com.dherrera.container.model.Response;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class CustomResponseSerializer extends StdSerializer<Response> {
    public CustomResponseSerializer() {
        this(null);
    }

    public CustomResponseSerializer(Class<Response> t) {
        super(t);
    }

    @Override
    public void serialize(Response value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeArrayFieldStart("Output");

        value.getAllocations().forEach(regionAllocation -> {
            try {
                gen.writeStartObject();
                gen.writeStringField("region", regionAllocation.getRegionName());
                gen.writeStringField("total_cost", "$" + regionAllocation.getTotalCost());

                gen.writeArrayFieldStart("machines");
                regionAllocation.getAllocatedMachines().forEach((machineName, amount) -> {
                    try {
                        gen.writeString("(" + machineName + ", " + amount + ")");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                gen.writeEndArray();

                gen.writeEndObject();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        gen.writeEndArray();
        gen.writeEndObject();
    }
}
