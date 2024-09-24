package es.ies.puerto.ejercicio7;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DataConsumer {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println("Received: " + line);
        }
    }
}
