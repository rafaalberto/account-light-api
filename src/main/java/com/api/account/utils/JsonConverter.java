package com.api.account.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public abstract class JsonConverter {

    public static String convertToJson(Object data) {
        var mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T readFromJson(String json, TypeReference<T> typeReference) {
        var mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
