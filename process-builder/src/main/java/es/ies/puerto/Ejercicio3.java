package es.ies.puerto;

import java.io.File;
import java.io.IOException;

public class Ejercicio3 {
    public boolean executeAndRedirectToFile(String command, String filePath) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
            processBuilder.redirectOutput(new File(filePath));
            Process process = processBuilder.start();
            int exitCode = process.waitFor(); // Esperar a que termine el proceso
            return exitCode == 0; // Retorna true si el proceso fue exitoso
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false; // Retornar false en caso de excepción
        }
    }
}
