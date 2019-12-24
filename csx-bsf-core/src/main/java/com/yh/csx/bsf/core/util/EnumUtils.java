package com.yh.csx.bsf.core.util;

/**
 * @author: chejiangyi
 * @version: 2019-07-23 18:38
 **/
public class EnumUtils {
    public static <T extends Enum<?>> T lookup(Class<T> enumType,String name) {
        for (T enumn : enumType.getEnumConstants()) {
            if (enumn.name().equalsIgnoreCase(name)) {
                return enumn;
            }
        }
        return null;
    }
}
