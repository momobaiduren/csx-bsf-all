package com.yh.csx.bsf.core.http;

import com.yh.csx.bsf.core.base.BsfException;

/**
 * @author: chejiangyi
 * @version: 2019-07-22 20:34
 **/
public class HttpException extends BsfException {
    public HttpException(Throwable exp)
    {
        super(exp);
    }

    public HttpException(String message)
    {
        super(message);
    }

    public HttpException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
