package es.ies.puerto.ejercicio8;


import java.util.Random;

public class Ejercicio8 {
    private static final int COMPETITION_DURATION = 5000;
    private static int thorWeightLifted = 0;
    private static int hulkWeightLifted = 0;
    private static boolean competitionEnded = false;

    public static class Competitor implements Runnable {
        private String name;

        public Competitor(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            Random random = new Random();
            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime < COMPETITION_DURATION && !competitionEnded) {
                int weight = random.nextInt(10) + 1;
                if (name.equals("Thor")) {
                    thorWeightLifted += weight;
                    System.out.println("Thor levantó " + weight + " kg. Peso total: " + thorWeightLifted + " kg.");
                } else {
                    hulkWeightLifted += weight;
                    System.out.println("Hulk levantó " + weight + " kg. Peso total: " + hulkWeightLifted + " kg.");
                }

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            synchronized (this) {
                competitionEnded = true;
            }
        }
    }

    public static void main(String[] args) {
        Thread thor = new Thread(new Competitor("Thor"));
        Thread hulk = new Thread(new Competitor("Hulk"));


        thor.start();
        hulk.start();

        try {
            thor.join();
            hulk.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (thorWeightLifted > hulkWeightLifted) {
            System.out.println("¡Thor ha ganado la competencia levantando " + thorWeightLifted + " kg!");
        } else if (hulkWeightLifted > thorWeightLifted) {
            System.out.println("¡Hulk ha ganado la competencia levantando " + hulkWeightLifted + " kg!");
        } else {
            System.out.println("¡Es un empate! Ambos levantaron " + thorWeightLifted + " kg.");
        }
    }
}
