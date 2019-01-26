package io.github.makbn.datatransmission.exception;

public class AccessDeniedException extends Exception {

    public AccessDeniedException(String message) {
        super(message);
    }

    public static AccessDeniedException getInstance() {
        return new AccessDeniedException("access denied!");

    }

    public static AccessDeniedException getInstance(String msg) {
        return new AccessDeniedException(msg);

    }
}
