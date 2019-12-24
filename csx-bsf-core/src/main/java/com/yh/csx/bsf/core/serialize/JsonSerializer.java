package com.yh.csx.bsf.core.serialize;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.yh.csx.bsf.core.base.BsfException;
import com.yh.csx.bsf.core.config.CoreProperties;
import com.yh.csx.bsf.core.util.PropertyUtils;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * @author Huang Zhaoping
 */
public class JsonSerializer implements Serializer {

    private static ObjectMapper objectMapper = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .enable(MapperFeature.USE_ANNOTATIONS)
            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .setTimeZone(TimeZone.getTimeZone(PropertyUtils.getPropertyCache(CoreProperties.SpringJacksonTimeZone,"GMT+8")))
            .setDateFormat(new SimpleDateFormat(PropertyUtils.getPropertyCache(CoreProperties.SpringJacksonDateFormat,"yyyy-MM-dd HH:mm:ss")));

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    @Override
    public String serialize(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new BsfException("序列化失败，对象：" + object, e);
        }
    }

    @Override
    public <T> T deserialize(String str, Type type) {
        try {
            return objectMapper.readValue(str, new TypeReference<Object>(){
                @Override
                public Type getType() {
                    return type;
                }
            });
        } catch (Exception e) {
            throw new BsfException("反序列化失败，类型：" + type + "，JSON：" + str, e);
        }
    }

}
