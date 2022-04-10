package com.tosan.tools.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @author Mostafa Abdollahi
 * @since 6/19/2021
 */
public class MinimalJsonWriter {
    private static final DefaultPrettyPrinter printer;
    private static final ObjectWriter writer;
    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        printer = new DefaultPrettyPrinter()
                .withObjectIndenter(new DefaultPrettyPrinter.FixedSpaceIndenter());
        writer = mapper.writer(printer);
    }

    public static ObjectMapper getMapper() {
        return mapper;
    }

    public static String writeJson(Object object) {
        try {
            return writer.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return ToStringConstant.ERROR_JSON + e.getMessage();
        }
    }
}
