package es.ies.puerto.ejercicio3;

/**
 * Simula una fábrica de droides en la que se están ensamblando droides de batalla. Un hilo se encarga de ensamblar
 * los droides, mientras que otro hilo se encarga de activarlos. Asegúrate de que los droides no se activen antes de
 * ser completamente ensamblados, usando mecanismos de sincronización entre hilos.
 */
public class Ejercicio3 {
    private boolean droidAssembled = false;


    public synchronized void assembleDroid() {
        try {
            System.out.println("Ensamblando un nuevo droide...");
            Thread.sleep(2000);
            droidAssembled = true;
            System.out.println("Droide ensamblado.");
            notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void activateDroid() {
        while (!droidAssembled) {
            try {
                System.out.println("Esperando a que el droide sea ensamblado...");
                wait(); // Espera hasta que el droide esté ensamblado
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Activando el droide...");
        droidAssembled = false; // Reiniciar para el próximo ciclo de ensamblaje
    }

    public static void main(String[] args) {
        Ejercicio3 factory = new Ejercicio3();

        Thread assembler = new Thread(() -> {
            for (int i = 0; i < 5; i++) { // Ensamblar 5 droides
                factory.assembleDroid();
            }
        });

        Thread activator = new Thread(() -> {
            for (int i = 0; i < 5; i++) { // Activar 5 droides
                factory.activateDroid();
            }
        });

        assembler.start();
        activator.start();

        try {
            assembler.join();
            activator.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Fábrica de droides ha terminado de ensamblar y activar todos los droides.");
    }
}
