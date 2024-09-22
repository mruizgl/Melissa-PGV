package es.ies.puerto;

public class Ejercicio6 {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            try {
                ProcessBuilder processBuilder = new ProcessBuilder("java", "WorkerClass");
                processBuilder.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
