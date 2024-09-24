import es.ies.puerto.Ejercicio3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

public class Ejercicio3Test {
    Ejercicio3 ejercicio3;

    @BeforeEach
    public void beforeEach() {
        ejercicio3 = new Ejercicio3();
    }

    @Test
    public void testExecuteAndRedirectToFileSuccess() {
        String filePath = "output.txt";
        boolean result = ejercicio3.executeAndRedirectToFile("ls", filePath);
        Assertions.assertTrue(result, "El comando debería ejecutarse correctamente y redirigir la salida al archivo.");
        File outputFile = new File(filePath);
        Assertions.assertTrue(outputFile.exists() && outputFile.length() > 0, "El archivo de salida debería existir y no estar vacío.");
        outputFile.delete();
    }

    @Test
    public void testExecuteAndRedirectToFileFailure() {
        String filePath = "output_fail.txt";
        boolean result = ejercicio3.executeAndRedirectToFile("invalidcommand", filePath);

        Assertions.assertFalse(result, "El comando inválido debería fallar.");

        File outputFile = new File(filePath);
        Assertions.assertFalse(outputFile.exists(), "El archivo de salida no debería existir para un comando fallido.");
    }
}
