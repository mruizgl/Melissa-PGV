import es.ies.puerto.ejercicio2.Ejercicio2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Imagina que Harry, Hermione y Ron están buscando Horrocruxes. Cada personaje está representado por un hilo que
 * busca en una ubicación diferente del mundo. El primer hilo que encuentra un Horrocrux debe detener a los demás
 * y terminar la búsqueda. Elige el tiempo de búsqueda de manera aleatoria para cada hilo.
 */
public class Ejercicio2Test {

    @Test
    public void testHorcruxHunt() throws InterruptedException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Thread harry = new Thread(new Ejercicio2("Harry"));
        Thread hermione = new Thread(new Ejercicio2("Hermione"));
        Thread ron = new Thread(new Ejercicio2("Ron"));

        harry.start();
        hermione.start();
        ron.start();

        harry.join();
        hermione.join();
        ron.join();

        String output = outContent.toString();

        Assertions.assertTrue(output.contains("ha encontrado un Horrocrux"));
    }
}
