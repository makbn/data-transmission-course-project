package io.github.makbn.datatransmission.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RestResponse<T> {

    private boolean error;
    private String message;
    private T result;
    private int code;

}
