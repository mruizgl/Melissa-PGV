package es.ies.puerto;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Ejercicio1 {

    public static void main(String[] args) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("ping", "google.com");
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}