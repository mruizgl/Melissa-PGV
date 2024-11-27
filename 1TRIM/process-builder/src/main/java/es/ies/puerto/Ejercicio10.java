package es.ies.puerto;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Ejercicio10 {
    private final ProcessBuilder processBuilder1;
    private final ProcessBuilder processBuilder2;

    public Ejercicio10(ProcessBuilder processBuilder1, ProcessBuilder processBuilder2) {
        this.processBuilder1 = processBuilder1;
        this.processBuilder2 = processBuilder2;
    }

    public void executeProcesses() throws IOException, InterruptedException {
        Process proc1 = processBuilder1.start();
        Process proc2 = processBuilder2.start();

        try (InputStream in = proc1.getInputStream();
             OutputStream out = proc2.getOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }

        proc1.waitFor();
        proc2.waitFor();
    }
}
