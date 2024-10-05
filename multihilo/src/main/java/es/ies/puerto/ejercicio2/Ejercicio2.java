package es.ies.puerto.ejercicio2;

import java.util.Random;

public class Ejercicio2 implements Runnable{
    private String name;
    private static boolean horcruxFound = false;

    public Ejercicio2(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        Random random = new Random();
        int searchTime = random.nextInt(5000) + 1000;

        System.out.println(name + " está buscando un Horrocrux...");

        try {
            Thread.sleep(searchTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (Ejercicio2.class) {
            if (!horcruxFound) {
                horcruxFound = true;
                System.out.println(name + " ha encontrado un Horrocrux!");
            } else {
                System.out.println(name + " se ha enterado de que ya se ha encontrado un Horrocrux y ha detenido la búsqueda.");
            }
        }
    }

    public static void main(String[] args) {
        Thread harry = new Thread(new Ejercicio2("Harry"));
        Thread hermione = new Thread(new Ejercicio2("Hermione"));
        Thread ron = new Thread(new Ejercicio2("Ron"));

        harry.start();
        hermione.start();
        ron.start();

        try {
            harry.join();
            hermione.join();
            ron.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("La búsqueda ha terminado.");
    }
}
