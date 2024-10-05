package es.ies.puerto.ejercicio9;


import java.util.Random;
/*
Crea una simulación del Millenium Falcon, donde Han Solo y Chewbacca están controlando la nave en medio de
una batalla espacial. Un hilo representa a Han Solo controlando la velocidad, mientras que otro hilo representa
a Chewbacca manejando los escudos. Si alguno de los sistemas falla, la nave podría ser destruida.
 */
public class Ejercicio9 {
    private static final int MAX_SPEED = 100; // Velocidad máxima de la nave
    private static final int SHIELD_FAILURE_RATE = 10; // Probabilidad de fallo de escudos (1 en 10)
    private static final int SPEED_FAILURE_RATE = 10; // Probabilidad de fallo de velocidad (1 en 10)
    private static boolean shipDestroyed = false; // Indica si la nave ha sido destruida

    public static class Pilot implements Runnable {
        private String name;

        public Pilot(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            Random random = new Random();

            while (!shipDestroyed) {
                if (name.equals("Han Solo")) {
                    int speed = random.nextInt(MAX_SPEED + 1);
                    System.out.println("Han Solo establece la velocidad en " + speed + ".");
                    if (random.nextInt(100) < SPEED_FAILURE_RATE) {
                        System.out.println("¡Fallo en el sistema de velocidad! La nave podría ser destruida.");
                        shipDestroyed = true;
                    }
                } else if (name.equals("Chewbacca")) {
                    System.out.println("Chewbacca maneja los escudos.");
                    if (random.nextInt(100) < SHIELD_FAILURE_RATE) { // 10% de probabilidad de fallo
                        System.out.println("¡Fallo en el sistema de escudos! La nave podría ser destruida.");
                        shipDestroyed = true;
                    }
                }

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("La nave ha sido destruida.");
        }
    }

    public static void main(String[] args) {

        Thread hanSolo = new Thread(new Pilot("Han Solo"));
        Thread chewbacca = new Thread(new Pilot("Chewbacca"));


        hanSolo.start();
        chewbacca.start();


        try {
            hanSolo.join();
            chewbacca.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("La misión ha terminado.");
    }
}

