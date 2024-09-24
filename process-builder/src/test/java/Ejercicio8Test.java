import es.ies.puerto.Ejercicio8;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class Ejercicio8Test {
    @Test
    public void testExecutePing() throws IOException, InterruptedException {
        ProcessBuilder mockProcessBuilder = mock(ProcessBuilder.class);
        Process mockProcess = mock(Process.class);

        when(mockProcessBuilder.start()).thenReturn(mockProcess);
        when(mockProcess.waitFor()).thenReturn(0); // 0 significa que terminó sin errores
        Ejercicio8 pingExecutor = new Ejercicio8(mockProcessBuilder);

        long executionTime = pingExecutor.executePing();

        verify(mockProcess).waitFor();

        assert(executionTime >= 0);
    }
}
