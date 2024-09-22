package es.ies.puerto;

public class Ejercicio8 {
    public static void main(String[] args) {
        try {
            long startTime = System.currentTimeMillis();
            ProcessBuilder processBuilder = new ProcessBuilder("ping", "-c", "4", "google.com");
            Process process = processBuilder.start();
            process.waitFor();
            long endTime = System.currentTimeMillis();
            System.out.println("Tiempo de ejecución: " + (endTime - startTime) + " ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
