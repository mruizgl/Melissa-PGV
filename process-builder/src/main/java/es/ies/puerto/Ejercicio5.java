package es.ies.puerto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ejercicio5 {
    public String executeCommand(String command) {
        StringBuilder errorOutput = new StringBuilder();
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
            Process process = processBuilder.start();
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line;
            while ((line = errorReader.readLine()) != null) {
                errorOutput.append(line).append("\n");
            }
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null; // Retorna null si ocurre una excepción
        }
        return errorOutput.toString().trim(); // Retorna la salida de error capturada
    }
}
