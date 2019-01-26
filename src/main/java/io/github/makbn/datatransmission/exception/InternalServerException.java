package io.github.makbn.datatransmission.exception;

public class InternalServerException extends Exception {

    public InternalServerException(String message) {
        super(message);
    }

    public static InternalServerException getInstance() {
        return new InternalServerException("internal error occurred!");

    }

    public static InternalServerException getInstance(String msg) {
        return new InternalServerException(msg);

    }
}
