import es.ies.puerto.ejercicio7.DataProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class Ejercicio7Test {
    DataProcessor dataProcessor;

    @BeforeEach
    public void beforeEach() {
        dataProcessor = new DataProcessor();
    }

    @Test
    public void testProcessData() throws IOException, InterruptedException {

        ProcessBuilder mockProducer = mock(ProcessBuilder.class);
        ProcessBuilder mockConsumer = mock(ProcessBuilder.class);
        Process mockProcProducer = mock(Process.class);
        Process mockProcConsumer = mock(Process.class);

        when(mockProducer.start()).thenReturn(mockProcProducer);
        when(mockConsumer.start()).thenReturn(mockProcConsumer);


        dataProcessor.processData();

        // Verificar que los procesos fueron iniciados
        verify(mockProcProducer).waitFor();
        verify(mockProcConsumer).waitFor();
    }
}
