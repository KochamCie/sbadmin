package com.beelego.sbadmin.local.model;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Serializable;

/**
 * @author : hama
 * @since : created in  2018/10/25
 */
public class BaseObject implements Serializable {
    private static final long serialVersionUID = 2L;


    // jackson转换工具
    public static final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            ;


    public <T extends BaseObject> T readValue(String data, Class<T> clazz) throws IOException {
        return objectMapper.readValue(data, clazz);
    }

}
