package es.ies.puerto;

import java.io.InputStream;
import java.io.OutputStream;

public class Ejercicio7 {
    public static void main(String[] args) {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
