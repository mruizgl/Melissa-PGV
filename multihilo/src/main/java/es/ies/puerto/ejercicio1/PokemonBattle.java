package es.ies.puerto.ejercicio1;

import java.util.Random;

/**
 * Crea una simulación de batalla Pokémon en la que dos Pokémon (Pikachu y Charmander) luchan entre sí. Cada hilo
 * representa a un Pokémon que alterna ataques y recibe daño hasta que uno de ellos se queda sin puntos de vida (HP).
 * Deberás lanzar dos hilos y hacer que se detengan cuando uno de los Pokémon gane.
 */
public class PokemonBattle implements Runnable{
    private String name;
    private int hp;

    private static int HEALTH = 80;
    private static boolean winnerDeclared = false;

    public PokemonBattle(String name, int hp) {
        this.name = name;
        this.hp = hp;
    }

    @Override
    public void run() {
        Random random = new Random();
        while ((HEALTH > 0) && !winnerDeclared) {
            int attack = random.nextInt(10);
            HEALTH -= attack;
            System.out.println(name + " atacó con " + attack + " de daño. Vida total: " + HEALTH + ".");

            if (HEALTH <= 0 && !winnerDeclared) {
                winnerDeclared = true;
                System.out.println(name + " ha ganado el combate");
            }

            try {
                Thread.sleep(500); // Pausa entre pasos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        Thread pikachu = new Thread(new PokemonBattle("Pikachu", 90));
        Thread charmander = new Thread(new PokemonBattle("charmander", 85));
        pikachu.start();
        charmander.start();
    }
}
