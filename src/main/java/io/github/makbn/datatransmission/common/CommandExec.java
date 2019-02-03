package io.github.makbn.datatransmission.common;

import io.github.makbn.datatransmission.domain.CommandResult;
import org.apache.commons.exec.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SuppressWarnings("Duplicates")
public class CommandExec {



    public static CommandResult execute(String cmd) throws IOException, InterruptedException {
        System.out.println("========================================");
        System.out.println("Command execution start...\n");
        return run(cmd);
    }

    public static CommandResult runScript(String path) throws InterruptedException, IOException {

        System.out.println("========================================");
        System.out.println("Script execution start...\n");

        return run(AppSettings.BASH_EXEC+" "+path);
    }


    private static CommandResult run(String command) throws InterruptedException, IOException {
        Process proc = Runtime.getRuntime().exec(command);

        // Read the output

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(proc.getInputStream()));

        BufferedReader errReader =
                new BufferedReader(new InputStreamReader(proc.getErrorStream()));



        String lineIn = "";
        String lineOut = "";
        String lineErr = "";

        String temp = "";
        while((temp = reader.readLine()) != null || proc.isAlive()) {
            if(temp==null) {
                Thread.sleep(1);
                continue;
            }
            System.out.println("\t > "+temp + "\n");
            lineIn = lineIn.concat(temp);
        }

        while((temp = errReader.readLine()) != null || proc.isAlive()) {
            if(temp==null) {
                Thread.sleep(1);
                continue;
            }
            System.err.println("\t > "+temp + "\n");
            lineErr = lineErr.concat(temp);
        }

        while((temp = errReader.readLine()) != null || proc.isAlive()) {
            if(temp==null) {
                Thread.sleep(1);
                continue;
            }
            System.err.println("\t > "+temp + "\n");
            lineErr = lineErr.concat(temp);
        }
        proc.waitFor();
        System.out.println("execution end!");
        System.out.println("========================================\n\n");

        CommandResult result= new CommandResult();
        result.setInput(lineIn);
        result.setInput(lineOut);
        result.setErr(lineErr);

        return result;
    }

}
