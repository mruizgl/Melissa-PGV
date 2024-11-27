import es.ies.puerto.Ejercicio6;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class Ejercicio6Test {
    Ejercicio6 ejercicio6;

    @Test
    public void runInstancesTest(){
        ejercicio6 = new Ejercicio6();
        Assertions.assertTrue(
                ejercicio6.startWorkers(5, "java", System.getProperty("java.class.path"), "es.ies.puerto.WorkerClass"));
        Assertions.assertFalse(
                ejercicio6.startWorkers(5, "java", System.getProperty("java.class.path"), "test"));
    }
}
