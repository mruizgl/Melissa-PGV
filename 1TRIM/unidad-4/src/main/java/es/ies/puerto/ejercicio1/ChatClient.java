package es.ies.puerto.ejercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Crea un programa de servidor que escuche en un puerto específico y un cliente que se conecte a este servidor.
 * El cliente enviará un mensaje al servidor, y el servidor le responderá con el mismo mensaje.
 */
public class ChatClient {
    public static void main(String[] args) {
        String direccionServidor = "localhost"; // Dirección del servidor
        int puerto = 12345; // Puerto del servidor

        try (Socket socket = new Socket(direccionServidor, puerto);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)) {

            // Enviar mensaje al servidor
            String mensaje = "Hola, servidor!";
            System.out.println("Enviando mensaje al servidor: " + mensaje);
            salida.println(mensaje);

            // Leer la respuesta del servidor
            String respuesta = entrada.readLine();
            System.out.println("Respuesta del servidor: " + respuesta);

        } catch (IOException e) {
            System.out.println("Error al comunicar con el servidor: " + e.getMessage());
        }
    }
}
