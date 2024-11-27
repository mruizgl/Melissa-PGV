

import es.ies.puerto.ejercicio10.Ejercicio10;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Ejercicio10Test {

    @Test
    public void testMagicalBattle() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));


        Ejercicio10.main(new String[]{});


        String output = outContent.toString();


        assertTrue(output.contains("ha ganado la batalla!"));
    }
}
