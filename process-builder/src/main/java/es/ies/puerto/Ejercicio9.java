package es.ies.puerto;

public class Ejercicio9 {
    public static void main(String[] args) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("ping", "-t", "google.com");
            Process process = processBuilder.start();
            Thread.sleep(10000); // Esperar 10 segundos
            process.destroy(); // Terminar el proceso
            System.out.println("Proceso terminado.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
