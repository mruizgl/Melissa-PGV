import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertTrue;



public class Ejercicio4Test {

    @Test
    public void testQuidditchMatch() throws InterruptedException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Thread chaserTeam1 = new Thread(new Ejercicio4.Chaser("Team 1"));
        Thread chaserTeam2 = new Thread(new Ejercicio4.Chaser("Team 2"));
        Thread seekerTeam1 = new Thread(new Ejercicio4.Seeker("Team 1"));
        Thread seekerTeam2 = new Thread(new Ejercicio4.Seeker("Team 2"));

        chaserTeam1.start();
        chaserTeam2.start();
        seekerTeam1.start();
        seekerTeam2.start();

        seekerTeam1.join();
        seekerTeam2.join();

        String output = outContent.toString();

        assertTrue(output.contains("ha atrapado la Snitch Dorada!"), "La Snitch debería haber sido atrapada.");

        assertTrue(output.contains("Puntuación final: Team 1:") && output.contains("Team 2:"),
                "La puntuación final debería haberse mostrado.");

        assertTrue(output.contains("¡Team 1 gana el partido!") || output.contains("¡Team 2 gana el partido!"),
                "El partido debería haber terminado con un ganador.");
    }
}


