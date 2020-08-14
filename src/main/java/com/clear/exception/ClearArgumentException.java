package com.clear.exception;

/**
 * ClassName ClearArgumentException
 *
 * @author qml
 * Date 2020/8/14 9:57
 * Version 1.0
 **/

public class ClearArgumentException extends RuntimeException{

    private static final long serialVersionUID = -2052514242913313569L;

    public ClearArgumentException() {
    }

    public ClearArgumentException(String message) {
        super(message);
    }

    public ClearArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClearArgumentException(Throwable cause) {
        super(cause);
    }
}