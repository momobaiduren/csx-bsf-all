package com.yh.csx.bsf.redis;

import com.yh.csx.bsf.core.base.BsfException;

/**
 * @author Huang Zhaoping
 */
public class RedisException extends BsfException {

    public RedisException(String message, Throwable cause) {
        super(message, cause);
    }

    public RedisException(Throwable exp) {
        super(exp);
    }

    public RedisException(String message) {
        super(message);
    }
}
