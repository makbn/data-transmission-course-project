package io.github.makbn.datatransmission.exception;

import io.github.makbn.datatransmission.domain.vo.ApiErrorVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class TDExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(TDExceptionHandler.class);

    @ExceptionHandler(
            {FileNotFoundException.class,
                    InternalServerException.class})
    public final ResponseEntity<?> handleExceptions(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();

        if (ex instanceof FileNotFoundException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            FileNotFoundException fileNotFoundException = (FileNotFoundException) ex;

            return handleUserNotFoundException(fileNotFoundException, headers, status, request);
        } else if (ex instanceof InvalidRequestException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            InvalidRequestException irex = (InvalidRequestException) ex;

            return handleInvalidRequestException(irex, headers, status, request);
        } else if (ex instanceof AccessDeniedException) {
            HttpStatus status = HttpStatus.FORBIDDEN;

            return handleAccessDeniedException((AccessDeniedException) ex, headers, status, request);
        } else {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("Unknown exception type: " + ex.getClass().getName());
            }

            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionInternal(ex, null, headers, status, request);
        }
    }

    private ResponseEntity<?> handleInvalidRequestException(InvalidRequestException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex, new ApiErrorVO(errors), headers, status, request);
    }


    private ResponseEntity<?> handleAccessDeniedException(AccessDeniedException ex,
                                                          HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex, new ApiErrorVO(errors), headers, status, request);
    }

    /**
     * Customize the response for UserNotFoundException.
     *
     * @param ex      The exception
     * @param headers The headers to be written to the response
     * @param status  The selected response status
     * @return a {@code ResponseEntity} instance
     */
    protected ResponseEntity<ApiErrorVO> handleUserNotFoundException(FileNotFoundException ex,
                                                                     HttpHeaders headers, HttpStatus status,
                                                                     WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex, new ApiErrorVO(errors), headers, status, request);
    }


    /**
     * A single place to customize the response body of all Exception types.
     *
     * <p>The default implementation sets the {@link WebUtils#ERROR_EXCEPTION_ATTRIBUTE}
     * request attribute and creates a {@link ResponseEntity} from the given
     * body, headers, and status.
     *
     * @param ex      The exception
     * @param body    The body for the response
     * @param headers The headers for the response
     * @param status  The response status
     * @param request The current request
     */
    protected ResponseEntity<ApiErrorVO> handleExceptionInternal(Exception ex, @Nullable ApiErrorVO body,
                                                                 HttpHeaders headers, HttpStatus status,
                                                                 WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        return new ResponseEntity<>(body, headers, status);
    }

}
