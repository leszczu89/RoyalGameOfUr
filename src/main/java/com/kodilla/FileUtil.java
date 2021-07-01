package com.kodilla;

import java.util.Objects;

public class FileUtil {
    public static String getFilePath(String resource){

        return Objects.requireNonNull(FileUtil.class.getResource(resource)).toString();

    }
}
