package es.ies.puerto;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class Mapa {
    private final int size;
    private final ConcurrentHashMap<String, int[]> ubicaciones;

    public Mapa(int size) {
        this.size = size;
        this.ubicaciones = new ConcurrentHashMap<>();
    }

    public int[] generarUbicacion() {
        Random rand = new Random();
        int x = rand.nextInt(size);
        int y = rand.nextInt(size);
        return new int[]{x, y};
    }


    public void moverPersonaje(String nombre, int[] nuevaUbicacion) {
        ubicaciones.put(nombre, nuevaUbicacion);
    }

    public boolean hayEncuentro(String nombreCazador, String nombreMonstruo) {
        int[] ubicacionCazador = ubicaciones.get(nombreCazador);
        int[] ubicacionMonstruo = ubicaciones.get(nombreMonstruo);

        return ubicacionCazador != null && ubicacionMonstruo != null
                && ubicacionCazador[0] == ubicacionMonstruo[0]
                && ubicacionCazador[1] == ubicacionMonstruo[1];
    }

    public void mostrarMapa() {
        for (String nombre : ubicaciones.keySet()) {
            int[] ubicacion = ubicaciones.get(nombre);
            System.out.println(nombre + " está en (" + ubicacion[0] + ", " + ubicacion[1] + ")");
        }
        System.out.println();
    }
}
