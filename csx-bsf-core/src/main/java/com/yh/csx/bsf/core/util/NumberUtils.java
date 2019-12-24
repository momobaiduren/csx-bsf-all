package com.yh.csx.bsf.core.util;

import java.math.BigDecimal;

/**
 * @author: chejiangyi
 * @version: 2019-07-28 14:40
 **/
public class NumberUtils {
    public static double scale(Number number, int scale){
        BigDecimal bg = new BigDecimal(number.doubleValue());
        return bg.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
