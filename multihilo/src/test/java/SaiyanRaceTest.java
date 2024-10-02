import es.ies.puerto.prueba.SaiyanRace;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class SaiyanRaceTest {
    @Test
    public void testSaiyanRace() throws InterruptedException {
        // Redirigir la salida estándar
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Ejecutar los hilos
        Thread goku = new Thread(new SaiyanRace("Goku"));
        Thread vegeta = new Thread(new SaiyanRace("Vegeta"));

        goku.start();
        vegeta.start();

        goku.join();  // Esperar a que termine Goku
        vegeta.join(); // Esperar a que termine Vegeta

        // Verificar si uno de ellos ganó
        String output = outContent.toString();
        Assertions.assertTrue(output.contains("Goku ha ganado la carrera!") || output.contains("Vegeta ha ganado la carrera!"));
    }
}
