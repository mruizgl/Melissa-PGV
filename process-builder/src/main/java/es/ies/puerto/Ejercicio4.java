package es.ies.puerto;

import java.io.IOException;

public class Ejercicio4 {
    public static boolean executeJavaProgram(String programName, String argument) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("java", programName, argument);
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            return exitCode == 0;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }
}
