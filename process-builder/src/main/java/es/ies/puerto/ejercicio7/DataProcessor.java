package es.ies.puerto.ejercicio7;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DataProcessor {
    public void processData() throws IOException, InterruptedException {
        ProcessBuilder producer = new ProcessBuilder("java", "DataProducer");
        ProcessBuilder consumer = new ProcessBuilder("java", "DataConsumer");

        Process procProducer = producer.start();
        Process procConsumer = consumer.start();

        try (InputStream in = procProducer.getInputStream();
             OutputStream out = procConsumer.getOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }

        procProducer.waitFor();
        procConsumer.waitFor();
    }
}
