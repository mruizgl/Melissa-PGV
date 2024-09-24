package es.ies.puerto;

import java.io.IOException;

public class Ejercicio9 {
    private final ProcessBuilder processBuilder;

    public Ejercicio9(ProcessBuilder processBuilder) {
        this.processBuilder = processBuilder;
    }

    public void executePing(long durationMillis) throws IOException, InterruptedException {
        Process process = processBuilder.start();

        Thread.sleep(durationMillis);

        process.destroy();
        System.out.println("Proceso terminado.");
    }
}
