package es.ies.puerto;

import java.io.IOException;

public class Ejercicio6 {
    public void startWorkers(int numberOfWorkers) {
        for (int i = 0; i < numberOfWorkers; i++) {
            try {
                ProcessBuilder processBuilder = new ProcessBuilder("java", "WorkerClass");
                processBuilder.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
