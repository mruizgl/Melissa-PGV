import es.ies.puerto.ejercicio9.Ejercicio9;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Ejercicio9Test {

    @Test
    public void testMillenniumFalconSimulation() throws InterruptedException {

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));


        Ejercicio9.main(new String[]{});


        String output = outContent.toString();

        assertTrue(output.contains("La nave ha sido destruida."));
    }
}
