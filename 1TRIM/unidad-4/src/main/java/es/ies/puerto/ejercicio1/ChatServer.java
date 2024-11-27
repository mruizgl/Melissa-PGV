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
public class ChatServer {
    public static void main(String[] args) {
        int puerto = 12345; // Puerto en el que escucha el servidor

        try (ServerSocket servidorSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en el puerto " + puerto);

            while (true) {
                // Espera una conexión del cliente
                try (Socket socketCliente = servidorSocket.accept();
                     BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
                     PrintWriter salida = new PrintWriter(socketCliente.getOutputStream(), true)) {

                    System.out.println("Cliente conectado");

                    // Leer el mensaje del cliente
                    String mensaje = entrada.readLine();
                    System.out.println("Mensaje recibido del cliente: " + mensaje);

                    // Enviar respuesta al cliente
                    salida.println("Servidor responde: " + mensaje);
                } catch (IOException e) {
                    System.out.println("Error al comunicar con el cliente: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al iniciar el servidor: " + e.getMessage());
        }
    }
}
