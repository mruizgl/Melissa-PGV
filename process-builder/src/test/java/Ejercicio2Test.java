import es.ies.puerto.Ejercicio2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Ejercicio2Test {
    Ejercicio2 ejercicio2;

   @Test
    public void testExecuteCommandsSuccess() {
       ejercicio2 = new Ejercicio2();
        String[] commands = { "echo 'Hola Mundo'", "ls", "echo 'Proceso finalizado'" };
        Assertions.assertTrue(ejercicio2.executeCommands(commands), "Los comandos deberían ejecutarse correctamente.");
    }

   @Test
    public void testExecuteCommandsFailure() {
       ejercicio2 = new Ejercicio2();
        String[] commands = { "comandoNoExistente", "echo 'Esto no debería ejecutarse'" };
        Assertions.assertFalse(ejercicio2.executeCommands(commands), "La ejecución debería fallar debido a un comando inexistente.");
    }
}
