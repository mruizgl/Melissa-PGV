import es.ies.puerto.Ejercicio4;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Ejercicio4Test {

    Ejercicio4 ejercicio4;


    @Test
    public void testExecuteJavaProgramFailure() {
        ejercicio4 = new Ejercicio4();
        boolean result = ejercicio4.executeJavaProgram("ProgramaInexistente", "Hola Mundo");
        Assertions.assertFalse(result, "La ejecución debería fallar debido a que el programa no existe.");
    }
}
