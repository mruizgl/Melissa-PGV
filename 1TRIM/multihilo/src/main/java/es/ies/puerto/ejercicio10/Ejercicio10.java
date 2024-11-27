package es.ies.puerto.ejercicio10;

/**
 * Simula una batalla mágica entre Gandalf y Saruman. Cada mago lanza hechizos que reducen la energía del otro mago,
 * usando dos hilos. El primer mago que agote por completo la energía del otro gana la batalla. Los hechizos deben
 * ser lanzados a intervalos de tiempo aleatorios.
 */

import java.util.Random;

public class Ejercicio10 {
    private static final int INITIAL_ENERGY = 100;
    private static boolean battleOver = false;

    public static class Wizard implements Runnable {
        private String name;
        private int energy;
        private Wizard opponent;

        public Wizard(String name, Wizard opponent) {
            this.name = name;
            this.energy = INITIAL_ENERGY;
            this.opponent = opponent;
        }

        @Override
        public void run() {
            Random random = new Random();

            while (!battleOver && energy > 0) {
                // Lanzar un hechizo
                int spellDamage = random.nextInt(20) + 1;
                opponent.reduceEnergy(spellDamage);
                System.out.println(name + " lanza un hechizo causando " + spellDamage + " puntos de daño. "
                        + opponent.name + " energía restante: " + opponent.energy);

                if (opponent.energy <= 0) {
                    System.out.println(name + " ha ganado la batalla!");
                    battleOver = true;
                    break;
                }

                try {
                    Thread.sleep(random.nextInt(1000) + 500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public synchronized void reduceEnergy(int amount) {
            energy -= amount;
        }
    }

    public static void main(String[] args) {
        Wizard saruman = new Wizard("Saruman", null);
        Wizard gandalf = new Wizard("Gandalf", saruman);
        saruman.opponent = gandalf;


        Thread threadGandalf = new Thread(gandalf);
        Thread threadSaruman = new Thread(saruman);

        threadGandalf.start();
        threadSaruman.start();


        try {
            threadGandalf.join();
            threadSaruman.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("La batalla ha terminado.");
    }
}

