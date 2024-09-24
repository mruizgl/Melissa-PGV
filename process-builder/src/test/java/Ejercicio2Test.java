import es.ies.puerto.Ejercicio2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Ejercicio2Test {
    Ejercicio2 ejercicio2;

    @BeforeEach
    public void beforeEach() {
        ejercicio2 = new Ejercicio2();
    }

    @Test
    public void testExecuteCommandsSuccess() {
        String[] commands = { "echo 'Hola Mundo'", "ls", "echo 'Proceso finalizado'" };
        Assertions.assertTrue(ejercicio2.executeCommands(commands), "Los comandos deberían ejecutarse correctamente.");
    }

    @Test
    public void testExecuteCommandsFailure() {
        String[] commands = { "comandoNoExistente", "echo 'Esto no debería ejecutarse'" };
        Assertions.assertFalse(ejercicio2.executeCommands(commands), "La ejecución debería fallar debido a un comando inexistente.");
    }
}
