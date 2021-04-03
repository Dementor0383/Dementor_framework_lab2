package com.github.Dementor0383.myException;

public class FrameworkException extends RuntimeException {

    public FrameworkException() {
    }

    public FrameworkException(String failLine) {
        super(failLine);
    }

    public FrameworkException(Object faiLine) {
        this(String.valueOf(faiLine));
        if (faiLine instanceof Throwable)
            initCause((Throwable) faiLine);
    }
}
