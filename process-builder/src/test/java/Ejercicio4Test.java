import es.ies.puerto.Ejercicio4;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Ejercicio4Test {

    Ejercicio4 ejercicio4;

    @BeforeEach
    public void beforeEach() {
        ejercicio4 = new Ejercicio4();
    }

    @Test
    public void testExecuteJavaProgramSuccess() {
        boolean result = ejercicio4 .executeJavaProgram("es.ies.puerto.java.ProgramaPrueba", "Hola Mundo");
        Assertions.assertTrue(result, "El programa debería ejecutarse correctamente.");
    }

    @Test
    public void testExecuteJavaProgramFailure() {
        boolean result = ejercicio4.executeJavaProgram("ProgramaInexistente", "Hola Mundo");
        Assertions.assertFalse(result, "La ejecución debería fallar debido a que el programa no existe.");
    }
}
