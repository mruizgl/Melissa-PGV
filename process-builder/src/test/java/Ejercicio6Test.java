import es.ies.puerto.Ejercicio6;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class Ejercicio6Test {
    Ejercicio6 ejercicio6;

    @BeforeEach
    public void beforeEach() {
        ejercicio6 = new Ejercicio6();
    }

    @Test
    public void testStartWorkers() throws IOException {
        ProcessBuilder mockProcessBuilder = mock(ProcessBuilder.class);
        when(mockProcessBuilder.start()).thenReturn(null);

        ejercicio6.startWorkers(5);

        verify(mockProcessBuilder, times(5)).start();
    }
}
