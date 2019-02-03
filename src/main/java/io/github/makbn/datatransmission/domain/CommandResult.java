package io.github.makbn.datatransmission.domain;

public class CommandResult {
    private String input;
    private String output;
    private String err;


    public CommandResult() {
    }

    public CommandResult(String input, String output, String err) {
        this.input = input;
        this.output = output;
        this.err = err;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }
}
