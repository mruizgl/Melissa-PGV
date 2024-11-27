package es.ies.puerto.ejercicio5;

import java.util.Random;

public class Ejercicio5 {
    private static boolean clueFound = false; // Indica si ya se encontró la pista
    private static final Object lock = new Object(); // Objeto para sincronizar hilos

    // Clase que representa a un Jedi que busca pistas sobre el Lado Oscuro
    static class Jedi implements Runnable {
        private String name;
        private String[] planets = {"Tatooine", "Endor", "Hoth", "Naboo", "Dagobah"};

        public Jedi(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            Random random = new Random();

            for (String planet : planets) {
                if (clueFound) {
                    break; // Si otro Jedi ya encontró la pista, detener la búsqueda
                }

                int searchTime = random.nextInt(3000) + 1000; // Tiempo aleatorio de búsqueda entre 1 y 4 segundos
                try {
                    System.out.println(name + " está buscando en " + planet + "...");
                    Thread.sleep(searchTime); // Simula el tiempo de búsqueda
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Chequear si encuentra la pista
                synchronized (lock) {
                    if (!clueFound) {
                        if (random.nextBoolean()) { // Probabilidad aleatoria de encontrar la pista
                            clueFound = true;
                            System.out.println(name + " ha encontrado una pista sobre el Lado Oscuro en " + planet + "!");
                        } else {
                            System.out.println(name + " no ha encontrado ninguna pista en " + planet + ".");
                        }
                    }
                }
            }

            // Si la búsqueda ha terminado y la pista fue encontrada
            if (clueFound) {
                System.out.println(name + " detiene la búsqueda. La pista ha sido encontrada.");
            }
        }
    }

    public static void main(String[] args) {
        // Crear los hilos para los dos exploradores Jedi
        Thread jedi1 = new Thread(new Jedi("Jedi 1"));
        Thread jedi2 = new Thread(new Jedi("Jedi 2"));

        // Iniciar la búsqueda
        jedi1.start();
        jedi2.start();

        // Esperar a que ambos terminen
        try {
            jedi1.join();
            jedi2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("La búsqueda ha terminado.");
    }
}
