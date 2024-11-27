import es.ies.puerto.Ejercicio1;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Ejercicio1Test {
    static Ejercicio1 ejercicio1;


    @Test
    public void testPingGoogle() {
        ejercicio1 = new Ejercicio1();
        String result = ejercicio1.ping("google.com");
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.contains("3 packets transmitted, 3 received"));
    }

    @Test
    public void testNoOKPingGoogle() {
        ejercicio1 = new Ejercicio1();
        String result = ejercicio1.ping("akshdjkasdh.com");
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.contains("3 packets transmitted, 3 received"));
    }
}
