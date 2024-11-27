package es.ies.puerto;

import java.io.IOException;

public class Ejercicio8 {
    private ProcessBuilder processBuilder;

    public Ejercicio8(ProcessBuilder processBuilder) {
        this.processBuilder = processBuilder;
    }

    public long executePing() throws IOException, InterruptedException {
        long startTime = System.currentTimeMillis();

        Process process = processBuilder.start();
        process.waitFor();

        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}
