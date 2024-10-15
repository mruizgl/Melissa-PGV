package es.ies.puerto;

public class Monstruo {
    private final String nombre;
    private int[] ubicacion;

    public Monstruo(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public int[] getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(int[] ubicacion) {
        this.ubicacion = ubicacion;
    }
}



