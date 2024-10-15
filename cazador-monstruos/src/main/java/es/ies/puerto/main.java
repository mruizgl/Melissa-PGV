package es.ies.puerto;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class main {
    public static void main(String[] args) throws InterruptedException {
        Mapa mapa = new Mapa(5);
        Monstruo monstruo = new Monstruo("Monstruo1");

        int[] ubicacionMonstruo = mapa.generarUbicacion();
        monstruo.setUbicacion(ubicacionMonstruo);
        mapa.moverPersonaje(monstruo.getNombre(), ubicacionMonstruo);

        Cazador cazador1 = new Cazador("Cazador1", mapa, monstruo);
        Cazador cazador2 = new Cazador("Cazador2", mapa, monstruo);

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(cazador1);
        executor.execute(cazador2);

        Thread.sleep(15000);
        executor.shutdownNow();

        executor.awaitTermination(1, TimeUnit.SECONDS);
        System.out.println("Juego terminado.");
    }
}
