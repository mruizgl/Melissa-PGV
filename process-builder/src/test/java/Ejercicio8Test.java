import es.ies.puerto.Ejercicio8;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class Ejercicio8Test {
    @Mock
    private ProcessBuilder processBuilder;

    @Mock
    private Process process;

    private Ejercicio8 ejercicio8;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ejercicio8 = new Ejercicio8(processBuilder);
    }


    @Test
    public void testExecutePing_IOException() throws IOException, InterruptedException {
        when(processBuilder.start()).thenThrow(new IOException("Mock IOException"));

        assertThrows(IOException.class, () -> ejercicio8.executePing());

        verify(processBuilder).start();
    }

    @Test
    public void testExecutePing_InterruptedException() throws IOException, InterruptedException {
        when(processBuilder.start()).thenReturn(process);
        when(process.waitFor()).thenThrow(new InterruptedException("Mock InterruptedException"));

        assertThrows(InterruptedException.class, () -> ejercicio8.executePing());

        verify(processBuilder).start();
        verify(process).waitFor();
    }
}
