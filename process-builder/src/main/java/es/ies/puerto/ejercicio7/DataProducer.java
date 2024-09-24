package es.ies.puerto.ejercicio7;

import java.io.OutputStream;

public class DataProducer {
    public static void main(String[] args) throws Exception {
        try (OutputStream out = System.out) {
            for (int i = 0; i < 10; i++) {
                out.write(("Data " + i + "\n").getBytes());
                out.flush();
                Thread.sleep(100); // Simula el tiempo de producción de datos
            }
        }
    }
}
