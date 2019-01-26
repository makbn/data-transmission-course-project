package io.github.makbn.datatransmission.controller;


import io.github.makbn.datatransmission.common.CommandExec;
import io.github.makbn.datatransmission.common.FileUtils;
import io.github.makbn.datatransmission.common.ScriptLoader;
import io.github.makbn.datatransmission.domain.Command;
import io.github.makbn.datatransmission.domain.CommandResult;
import io.github.makbn.datatransmission.domain.ResFact;
import io.github.makbn.datatransmission.exception.InternalServerException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/terminal")
public class ScriptController {


    @GetMapping("/")
    public ResponseEntity<?> hello() throws InternalServerException {
        try {
            File script = ScriptLoader.load("sample.sh");
            String cmd = FileUtils.readFile(script);

            CommandResult result = CommandExec.runScript(script.getAbsolutePath());
            return ResponseEntity.ok(ResFact.<CommandResult>build().setResult(result).get());
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            throw InternalServerException.getInstance(e.getMessage());
        }
    }

    @PostMapping("/command")
    public ResponseEntity<?> command(@RequestBody Command command) throws InternalServerException {
        try {
            CommandResult result = CommandExec.execute(command.getCmd());
            return ResponseEntity.ok(ResFact.<CommandResult>build().setResult(result).get());
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            throw InternalServerException.getInstance(e.getMessage());
        }
    }


    @GetMapping("/ui")
    public void help(HttpServletResponse response,
                     HttpServletRequest request) {
        try {
            response.sendRedirect("/api/swagger-ui.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}