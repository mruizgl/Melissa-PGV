import es.ies.puerto.ejercicio1.PokemonBattle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Ejercicio1Test {

    @Test
    public void testPokemonBattle() throws InterruptedException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Thread pikachu = new Thread(new PokemonBattle("Pikachu"));
        Thread charmander = new Thread(new PokemonBattle("Charmander"));

        pikachu.start();
        charmander.start();

        pikachu.join();
        charmander.join();

        String output = outContent.toString();

        Assertions.assertTrue(output.contains("Pikachu ha sido derrotado.") || output.contains("Charmander ha sido derrotado."));
    }
}


