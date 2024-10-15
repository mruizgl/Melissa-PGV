package es.ies.puerto;

import java.util.Random;

public class Mapa {
    private final int size;
    private final String[][] mapa;

    public Mapa(int size) {
        this.size = size;
        this.mapa = new String[size][size];
        inicializarMapa();
    }

    private void inicializarMapa() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                mapa[i][j] = ".";
            }
        }
    }

    public synchronized void moverPersonaje(String nombre, int[] nuevaUbicacion) {
        limpiarMapaDePersonaje(nombre);  // Elimina al personaje de su ubicación anterior
        mapa[nuevaUbicacion[0]][nuevaUbicacion[1]] = nombre.equals("Monstruo1") ? "M" : "C";
    }

    private void limpiarMapaDePersonaje(String nombre) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (mapa[i][j].equals(nombre.equals("Monstruo1") ? "M" : "C")) {
                    mapa[i][j] = ".";
                }
            }
        }
    }

    public synchronized int[] generarUbicacion() {
        Random rand = new Random();
        int x = rand.nextInt(size);
        int y = rand.nextInt(size);
        return new int[]{x, y};
    }

    public synchronized void mostrarMapa() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(mapa[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }


    public synchronized boolean hayEncuentro(int[] ubicacionCazador, int[] ubicacionMonstruo) {
        return ubicacionCazador[0] == ubicacionMonstruo[0] && ubicacionCazador[1] == ubicacionMonstruo[1];
    }
}
