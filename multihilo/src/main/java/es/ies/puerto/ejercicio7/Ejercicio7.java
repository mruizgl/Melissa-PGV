package es.ies.puerto.ejercicio7;

import java.util.Random;

public class Ejercicio7 {
    private static boolean threatNeutralized = false;
    private static final Object lock = new Object();

    public static class Superhero implements Runnable {
        private String name;
        private String[] areas;

        public Superhero(String name, String[] areas) {
            this.name = name;
            this.areas = areas;
        }

        @Override
        public void run() {
            Random random = new Random();

            for (String area : areas) {
                if (threatNeutralized) {
                    break;
                }

                int saveTime = random.nextInt(3000) + 1000;
                try {
                    System.out.println(name + " está salvando la zona " + area + "...");
                    Thread.sleep(saveTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock) {
                    if (!threatNeutralized) {
                        System.out.println(name + " ha salvado la zona " + area + ".");
                    }

                    if (area.equals(areas[areas.length - 1]) && !threatNeutralized) {
                        threatNeutralized = true;
                        System.out.println(name + " ha neutralizado la amenaza salvando todas sus áreas!");
                    }
                }
            }

            if (threatNeutralized) {
                System.out.println(name + " detiene su esfuerzo. La amenaza ha sido neutralizada.");
            }
        }
    }

    public static void main(String[] args) {
        String[] supermanAreas = {"Zona 1", "Zona 2", "Zona 3", "Zona 4"};
        String[] batmanAreas = {"Zona A", "Zona B", "Zona C", "Zona D"};

        Thread superman = new Thread(new Superhero("Superman", supermanAreas));
        Thread batman = new Thread(new Superhero("Batman", batmanAreas));

        superman.start();
        batman.start();

        try {
            superman.join();
            batman.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("La ciudad ha sido salvada.");
    }
}
