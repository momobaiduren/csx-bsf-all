package com.yh.csx.bsf.core.serialize;

import com.fasterxml.jackson.core.type.TypeReference;

import java.lang.reflect.Type;

/**
 * @author Huang Zhaoping
 */
public interface Serializer {

    String serialize(Object object);

    <T> T deserialize(String str, Type type);

}
