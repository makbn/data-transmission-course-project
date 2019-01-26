package io.github.makbn.datatransmission.common;

import io.github.makbn.datatransmission.exception.FileNotFoundException;

import java.io.File;
import java.io.IOException;

public class ScriptLoader {

    public static File load(String name) throws IOException {
        return new File(ClassLoader.getSystemClassLoader()
                .getResource(AppSettings.BASH_FILE_DIR + name).getFile());
    }

    public static String getContent(String name) throws IOException {
        File bash = load(name);
        if(bash == null)
            throw FileNotFoundException.get("bash file not found!");
        String content = FileUtils.readFile(bash);
        return content;
    }
}
