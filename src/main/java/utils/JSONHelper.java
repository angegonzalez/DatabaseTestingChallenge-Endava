package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JSONHelper {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T jsonToPOJO(File json, Class<T> tclass) throws IOException {
        return objectMapper.readValue(json, tclass);
    }
}
