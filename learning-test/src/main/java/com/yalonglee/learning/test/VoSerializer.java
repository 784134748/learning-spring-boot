package com.yalonglee.learning.test;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.yalonglee.learning.core.utils.Json2;

import java.io.IOException;

public class VoSerializer extends JsonSerializer implements ContextualSerializer {

    private int index = 0;
    private String[] properties;

    public VoSerializer() {
    }

    public VoSerializer(String[] properties) {
        this.properties = properties;
    }

    @Override
    public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        System.out.println(index);
        System.out.println(value.getClass().getName());
        String val = value.toString();
        jgen.writeString(val);
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        return new VoSerializer(properties);
    }

    public static void main(String[] args) {
        UserDictionaryVO user = UserDictionaryVO.builder()
                .username("yalonglee")
                .email("784134748@qq.com")
                .build();

        System.out.println(Json2.toJson(user));
    }
}