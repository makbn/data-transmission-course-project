package io.github.makbn.datatransmission.domain;

public class RestResponse<T> {

    private boolean error;
    private String message;
    private T result;
    private int code;

    public RestResponse() {
    }

    public RestResponse(boolean error, String message, T result, int code) {
        this.error = error;
        this.message = message;
        this.result = result;
        this.code = code;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
