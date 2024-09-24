package es.ies.puerto;

import java.io.IOException;

public class Ejercicio4 {
    public static boolean executeJavaProgram(String programName, String argument) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("java", programName, argument);
            Process process = processBuilder.start();
            int exitCode = process.waitFor(); // Esperar a que el proceso finalice
            return exitCode == 0; // Retorna true si el proceso fue exitoso
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false; // Retornar false en caso de excepción
        }
    }
}
