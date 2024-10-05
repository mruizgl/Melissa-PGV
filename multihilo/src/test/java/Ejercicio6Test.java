import java.util.Random;

public class Ejercicio6Test {
    private static boolean destinationReached = false;
    private static final Object lock = new Object();

    static class TimeTraveler implements Runnable {
        private String era;

        public TimeTraveler(String era) {
            this.era = era;
        }

        @Override
        public void run() {
            Random random = new Random();
            int travelTime = random.nextInt(4000) + 1000;

            System.out.println("El viaje a la época " + era + " ha comenzado.");

            try {
                Thread.sleep(travelTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lock) {
                if (!destinationReached) {
                    destinationReached = true;
                    System.out.println("La TARDIS ha llegado a la época " + era + "!");
                } else {
                    System.out.println("El viaje a la época " + era + " terminó, pero el destino ya fue alcanzado.");
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread ancientEgypt = new Thread(new TimeTraveler("Antiguo Egipto"));
        Thread futureSpace = new Thread(new TimeTraveler("Futuro Espacial"));
        Thread medievalTimes = new Thread(new TimeTraveler("Edad Media"));
        Thread prehistoricEra = new Thread(new TimeTraveler("Era Prehistórica"));


        ancientEgypt.start();
        futureSpace.start();
        medievalTimes.start();
        prehistoricEra.start();


        try {
            ancientEgypt.join();
            futureSpace.join();
            medievalTimes.join();
            prehistoricEra.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("El viaje en el tiempo ha concluido.");
    }
}
