package com.everyme.global.security.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ConvertUtil {

    /* Json 형식으로 변환하는 메소드 */

    public static Object convertObjectToJsonObject(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        JSONParser parser = new JSONParser();
        String convertJsonString;
        Object convertObj;

        try {
            convertJsonString = mapper.writeValueAsString(obj);
            convertObj = parser.parse(convertJsonString);
        } catch (JsonProcessingException | ParseException e) {
            throw new RuntimeException(e);
        }

        return convertObj;
    }

}
