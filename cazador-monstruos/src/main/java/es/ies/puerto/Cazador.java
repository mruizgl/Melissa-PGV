package es.ies.puerto;

import java.util.Random;

public class Cazador extends Thread{
    private final String nombre;
    private final Mapa mapa;
    private final Monstruo monstruo;
    private final Random rand = new Random();
    private int monstruosAtrapados = 0;
    private int[] ubicacion;

    public Cazador(String nombre, Mapa mapa, Monstruo monstruo) {
        this.nombre = nombre;
        this.mapa = mapa;
        this.monstruo = monstruo;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                ubicacion = mapa.generarUbicacion();
                mapa.moverPersonaje(nombre, ubicacion);
                mapa.mostrarMapa();

                if (mapa.hayEncuentro(ubicacion, monstruo.getUbicacion())) {
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
