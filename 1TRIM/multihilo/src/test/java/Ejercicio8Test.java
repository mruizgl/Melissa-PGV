package es.ies.puerto.ejercicio8;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Ejercicio8Test {

    @Test
    public void testStrengthCompetition()  {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Ejercicio8.main(new String[]{});

        String output = outContent.toString();

        assertTrue(output.contains("ha ganado la competencia levantando") ||
                output.contains("¡Es un empate!"));
    }
}
