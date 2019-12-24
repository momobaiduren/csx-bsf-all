package com.yh.csx.bsf.file;


import com.yh.csx.bsf.core.base.BsfException;

/**
 * @author Huang Zhaoping
 */
public class FileException extends BsfException {

    public FileException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileException(String message) {
        super(message);
    }
}
