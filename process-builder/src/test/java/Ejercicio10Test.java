import es.ies.puerto.Ejercicio10;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class Ejercicio10Test {
    @Test
    public void testExecuteProcesses() throws IOException, InterruptedException {
        ProcessBuilder mockProcessBuilder1 = mock(ProcessBuilder.class);
        ProcessBuilder mockProcessBuilder2 = mock(ProcessBuilder.class);
        Process mockProcess1 = mock(Process.class);
        Process mockProcess2 = mock(Process.class);
        InputStream mockInputStream = mock(InputStream.class);
        OutputStream mockOutputStream = mock(OutputStream.class);

        when(mockProcessBuilder1.start()).thenReturn(mockProcess1);
        when(mockProcessBuilder2.start()).thenReturn(mockProcess2);
        when(mockProcess1.getInputStream()).thenReturn(mockInputStream);
        when(mockProcess2.getOutputStream()).thenReturn(mockOutputStream);

        when(mockInputStream.read(any(byte[].class))).thenReturn(4).thenReturn(-1); // Simula que lee 4 bytes y luego finaliza

        Ejercicio10 processExecutor = new Ejercicio10(mockProcessBuilder1, mockProcessBuilder2);


        processExecutor.executeProcesses();

        verify(mockProcess1).waitFor();
        verify(mockProcess2).waitFor();


        verify(mockOutputStream).write(any(byte[].class), eq(0), eq(4));
    }
}
