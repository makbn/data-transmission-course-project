package io.github.makbn.datatransmission.domain;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResFact<T> {

    private RestResponse restResponse;

    private ResFact(RestResponse restResponse) {
        this.restResponse = restResponse;
        //Do NOT CREATE
    }

    public static <T> ResFact<T> build() {
        RestResponse<T> restResponse = new RestResponse();
        restResponse.setCode(200);
        restResponse.setMessage("OK");
        restResponse.setError(false);

        return new ResFact<T>(restResponse);
    }

    public ResFact<T> setResult(T result) {
        this.restResponse.setResult(result);
        return this;
    }

    public ResFact<T> setError(boolean hasError) {
        this.restResponse.setError(hasError);
        return this;
    }

    public ResFact<T> setMessage(String message) {
        this.restResponse.setMessage(message);
        return this;
    }

    public ResFact<T> setCode(HttpStatus status) {
        this.restResponse.setCode(status.value());
        return this;
    }

    public RestResponse<T> get() {
        return restResponse;
    }
}
