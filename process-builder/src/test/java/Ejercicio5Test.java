import es.ies.puerto.Ejercicio5;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Ejercicio5Test {
    Ejercicio5 ejercicio5;

    @BeforeEach
    public void beforeEach() {
        ejercicio5 = new Ejercicio5();
    }

    @Test
    public void testExecuteCommandWithInvalidCommand() {
        String errorOutput = ejercicio5.executeCommand("comando_inexistente");

        Assertions.assertNotNull(errorOutput, "Debería haber un mensaje de error.");
        Assertions.assertFalse(errorOutput.isEmpty(), "El mensaje de error no debería estar vacío.");
        System.out.println("Mensaje de error capturado: " + errorOutput);
    }

    @Test
    public void testExecuteCommandWithValidCommand() {
        String errorOutput = ejercicio5.executeCommand("echo Hola");

        Assertions.assertTrue(errorOutput.isEmpty(), "No debería haber un mensaje de error para un comando válido.");
    }
}
