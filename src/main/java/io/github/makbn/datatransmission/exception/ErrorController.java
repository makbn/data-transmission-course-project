package io.github.makbn.datatransmission.exception;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/error")
public class ErrorController {

    @RequestMapping(value = "/access-denied", method = RequestMethod.GET)
    public void accessDenied() throws AccessDeniedException {
        throw AccessDeniedException.getInstance();
    }
}
