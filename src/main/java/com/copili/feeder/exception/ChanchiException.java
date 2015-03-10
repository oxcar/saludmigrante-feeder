package com.copili.feeder.exception;

import org.apache.commons.lang.StringUtils;

public class ChanchiException extends Exception {

    private static final long serialVersionUID = 6797960444105480794L;

    private String message;

    public ChanchiException( String message ) {
        this.message = StringUtils.isNotBlank( message ) ? message : "--";
    }

    public String getMessage() {
        return this.message;
    }

}
