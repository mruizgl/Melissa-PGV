import es.ies.puerto.Ejercicio1;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Ejercicio1Test {
    Ejercicio1 ejercicio1;

    @BeforeEach
    public void Ejercicio1 () {
        ejercicio1 = new Ejercicio1();
    }

    @Test
    public void testPingGoogle() {
        String result = ejercicio1.ping("google.com");
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.contains("PING") || result.contains("ping")); // Verificar si la salida contiene "PING" (dependerá del sistema operativo)
    }
}
