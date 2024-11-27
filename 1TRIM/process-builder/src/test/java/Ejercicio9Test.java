import es.ies.puerto.Ejercicio9;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class Ejercicio9Test {

    @Test
    public void testExecutePing() throws IOException, InterruptedException {
        ProcessBuilder mockProcessBuilder = mock(ProcessBuilder.class);
        Process mockProcess = mock(Process.class);

        when(mockProcessBuilder.start()).thenReturn(mockProcess);

        Ejercicio9 pingExecutor = new Ejercicio9(mockProcessBuilder);


        long durationMillis = 10000;
        pingExecutor.executePing(durationMillis);

        verify(mockProcess).destroy();
    }
}
