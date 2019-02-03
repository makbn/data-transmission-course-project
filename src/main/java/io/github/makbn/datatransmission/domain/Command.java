package io.github.makbn.datatransmission.domain;

public class Command {
    private String port;
    private String type;
    private String device;
    private String cmd;


    public Command() {
    }

    public Command(String port, String type, String device, String cmd) {
        this.port = port;
        this.type = type;
        this.device = device;
        this.cmd = cmd;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }
}
