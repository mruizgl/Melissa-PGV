package es.ies.puerto;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Ejercicio5 {
    public static void main(String[] args) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("comando_inexistente");
            Process process = processBuilder.start();
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line;
            while ((line = errorReader.readLine()) != null) {
                System.err.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
