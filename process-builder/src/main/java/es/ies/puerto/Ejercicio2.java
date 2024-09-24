package es.ies.puerto;

import java.io.IOException;

public class Ejercicio2 {
    public static boolean executeCommands(String[] commands) {
        try {
            for (String command : commands) {
                ProcessBuilder processBuilder = new ProcessBuilder("sh", "-c", command);
                Process process = processBuilder.start();
                int exitCode = process.waitFor(); // Esperar a que termine el proceso
                if (exitCode != 0) {
                    return false; // Si el comando falla, retorna false
                }
            }
            return true; // Retorna true si todos los comandos se ejecutaron correctamente
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false; // En caso de excepción, retornar false
        }
    }
    
}
