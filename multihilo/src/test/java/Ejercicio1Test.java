import es.ies.puerto.ejercicio1.PokemonBattle;
import es.ies.puerto.prueba.SaiyanRace;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Ejercicio1Test {
    @Test
    public void pokemonBattleTest() throws InterruptedException {
        // Redirigir la salida estándar
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Ejecutar los hilos
        Thread pikachu = new Thread(new PokemonBattle("Pikachu", 90));
        Thread charmander = new Thread(new PokemonBattle("Charmander", 85));

        pikachu.start();
        charmander.start();

        pikachu.join();  // Esperar a que termine Goku
        charmander.join(); // Esperar a que termine Vegeta

        // Verificar si uno de ellos ganó
        String output = outContent.toString();

        Assertions.assertTrue(output.contains("Pikachu ha ganado la carrera!") || output.contains("Charmander  ha ganado la carrera!"));
    }
}
