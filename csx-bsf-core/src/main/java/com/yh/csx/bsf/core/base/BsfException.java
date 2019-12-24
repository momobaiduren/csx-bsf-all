package com.yh.csx.bsf.core.base;

/**
 * @author: chejiangyi
 * @version: 2019-06-12 14:34
 **/
public class BsfException extends RuntimeException {

    private Throwable source;

    public BsfException(Throwable exp)
    {
        super(exp);
        this.source = exp;
    }

    public BsfException(String message)
    {
        super(message);
    }

    public BsfException(String message, Throwable cause)
    {
        super(message, cause);
        this.source = cause;
    }

    public Throwable getSource(){
        if(this.source == null)
        {    return null;}
        if(this.source instanceof BsfException)
        {   return  ((BsfException)this.source).getSource();}
        return source;
    }
}
