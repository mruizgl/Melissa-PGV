import es.ies.puerto.ejercicio3.Ejercicio3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Ejercicio3Test {
    @Test
    public void testDroidFactory() throws InterruptedException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Ejercicio3 factory = new Ejercicio3();

        Thread assembler = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                factory.assembleDroid();
            }
        });

        Thread activator = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                factory.activateDroid();
            }
        });

        assembler.start();
        activator.start();

        assembler.join();
        activator.join();

        String output = outContent.toString();
        Assertions.assertTrue(output.contains("Droide ensamblado."), "El droide debería haberse ensamblado.");
        Assertions.assertTrue(output.contains("Activando el droide..."), "El droide debería haberse activado.");

        int indexAssembled = output.indexOf("Droide ensamblado.");
        int indexActivated = output.indexOf("Activando el droide...");

        Assertions.assertTrue(indexAssembled < indexActivated, "Los droides deben ensamblarse antes de ser activados.");
    }
}
