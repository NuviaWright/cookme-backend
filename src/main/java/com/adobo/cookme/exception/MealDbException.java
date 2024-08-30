package com.adobo.cookme.exception;

public class MealDbException extends Exception {
    public MealDbException() {
        super();
    }

    public MealDbException(String message) {
        super(message);
    }

    public MealDbException(String message, Throwable cause) {
        super(message, cause);
    }

    public MealDbException(Throwable cause) {
        super(cause);
    }

    protected MealDbException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
