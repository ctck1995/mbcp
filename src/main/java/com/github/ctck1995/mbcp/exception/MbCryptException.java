package com.github.ctck1995.mbcp.exception;

public class MbCryptException extends RuntimeException {

    private static final long serialVersionUID = 4852711717315752783L;

    public MbCryptException() {
        super();
    }

    public MbCryptException(String message) {
        super(message);
    }

    public MbCryptException(String message, Throwable cause) {
        super(message, cause);
    }

    public MbCryptException(Throwable cause) {
        super(cause);
    }

    protected MbCryptException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
