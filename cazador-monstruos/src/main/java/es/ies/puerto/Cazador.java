package es.ies.puerto;

import java.util.Random;

public class Cazador extends Thread{
    private final String nombre;
    private final Mapa mapa;
    private final Monstruo monstruo;
    private final Random rand = new Random();
    private int monstruosAtrapados = 0;

    public Cazador(String nombre, Mapa mapa, Monstruo monstruo) {
        this.nombre = nombre;
        this.mapa = mapa;
        this.monstruo = monstruo;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                int[] nuevaUbicacion = mapa.generarUbicacion();
                mapa.moverPersonaje(nombre, nuevaUbicacion);
                mapa.mostrarMapa();


                if (mapa.hayEncuentro(nombre, monstruo.getNombre())) {
                    if (rand.nextInt(100) < 70) {
                        System.out.println(nombre + " atrapó un monstruo!");
                        monstruosAtrapados++;
                        break;
                    }
                }

                Thread.sleep(rand.nextInt(3000));
            }
        } catch (InterruptedException e) {
            System.out.println(nombre + " terminó la caza. Monstruos atrapados: " + monstruosAtrapados);
        }
    }
}
