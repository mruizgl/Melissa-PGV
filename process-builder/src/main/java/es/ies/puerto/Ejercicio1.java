package es.ies.puerto;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Ejercicio1 {

    public static String ping(String host) {
        StringBuilder result = new StringBuilder();
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("ping", host);
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return result.toString();
    }

}