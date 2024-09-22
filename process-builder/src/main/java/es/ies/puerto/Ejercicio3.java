package es.ies.puerto;

import java.io.File;

public class Ejercicio3 {
    public static void main(String[] args) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("ls");
            processBuilder.redirectOutput(new File("output.txt"));
            Process process = processBuilder.start();
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
