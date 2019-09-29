package com.api.account.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class AppUtils {

    public static String convertToJson(Object data) {
        var mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T readFromJson(String json) {
        var mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, new TypeReference<T>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
