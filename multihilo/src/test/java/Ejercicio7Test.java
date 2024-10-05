import es.ies.puerto.ejercicio7.Ejercicio7;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Ejercicio7Test {

    @Test
    public void testSuperheroSimulation() throws InterruptedException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String[] supermanAreas = {"Zona 1", "Zona 2", "Zona 3", "Zona 4"};
        String[] batmanAreas = {"Zona A", "Zona B", "Zona C", "Zona D"};

        Thread superman = new Thread(new Ejercicio7.Superhero("Superman", supermanAreas));
        Thread batman = new Thread(new Ejercicio7.Superhero("Batman", batmanAreas));

        superman.start();
        batman.start();

        superman.join();
        batman.join();

        String output = outContent.toString();
        assertTrue(output.contains("ha neutralizado la amenaza salvando todas sus áreas"));
    }
}
