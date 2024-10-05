package es.ies.puerto.ejercicio6;

import java.util.Random;

public class Ejercicio6 {
    private static boolean clueFound = false;
    private static final Object lock = new Object();

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
                    break;
                }

                int searchTime = random.nextInt(3000) + 1000;
                try {
                    System.out.println(name + " está buscando en " + planet + "...");
                    Thread.sleep(searchTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock) {
                    if (!clueFound) {
                        if (random.nextBoolean()) {
                            clueFound = true;
                            System.out.println(name + " ha encontrado una pista sobre el Lado Oscuro en " + planet + "!");
                        } else {
                            System.out.println(name + " no ha encontrado ninguna pista en " + planet + ".");
                        }
                    }
                }
            }

            if (clueFound) {
                System.out.println(name + " detiene la búsqueda. La pista ha sido encontrada.");
            }
        }
    }

    public static void main(String[] args) {
        Thread jedi1 = new Thread(new Jedi("Jedi 1"));
        Thread jedi2 = new Thread(new Jedi("Jedi 2"));

        jedi1.start();
        jedi2.start();

        try {
            jedi1.join();
            jedi2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("La búsqueda ha terminado.");
    }

}
