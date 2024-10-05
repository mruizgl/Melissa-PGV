package es.ies.puerto.ejercicio1;

import java.util.Random;

/**
 * Crea una simulación de batalla Pokémon en la que dos Pokémon (Pikachu y Charmander) luchan entre sí. Cada hilo
 * representa a un Pokémon que alterna ataques y recibe daño hasta que uno de ellos se queda sin puntos de vida (HP).
 * Deberás lanzar dos hilos y hacer que se detengan cuando uno de los Pokémon gane.
 */
public class PokemonBattle implements Runnable {
    private String name;
    private int hp = 100;
    private static boolean battleOver = false;
    private final Object lock = new Object();
    private static Random random = new Random();

    public PokemonBattle(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (hp > 0 && !battleOver) {
            attack();
            if (hp <= 0) {
                battleOver = true;
                System.out.println(name + " ha sido derrotado.");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void attack() {
        synchronized (lock) {
            if (!battleOver) {
                int damage = random.nextInt(20) + 1; // Daño aleatorio entre 1 y 20
                hp -= damage;
                if (hp < 0) hp = 0;
                System.out.println(name + " recibió " + damage + " puntos de daño. HP restante: " + hp);
            }
        }
    }

    public static void main(String[] args) {
        Thread pikachu = new Thread(new PokemonBattle("Pikachu"));
        Thread charmander = new Thread(new PokemonBattle("Charmander"));

        pikachu.start();
        charmander.start();

        try {
            pikachu.join();
            charmander.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("La batalla ha terminado.");
    }
}

