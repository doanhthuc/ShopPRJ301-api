package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpUtil {

    private final String value;

    public HttpUtil(String value) {
        this.value = value;
    }

    public <T> T toModel(Class<T> tClass) {
        try {
            return new ObjectMapper().readValue(value, tClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static HttpUtil of (BufferedReader reader) {
        StringBuilder sb = new StringBuilder();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append((line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HttpUtil(sb.toString());
    }

    public String getValue() {
        return value;
    }
}
