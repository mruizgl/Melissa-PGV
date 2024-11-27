package es.ies.puerto.ejercicio2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Extiende (ServidorContinua extend Servidor) el ejercicio anterior para que el cliente y el servidor puedan
 * mantener una conversación continua. El cliente podrá enviar varios mensajes al servidor, y el servidor devolverá
 * cada mensaje hasta que el cliente envíe el mensaje "salir", lo cual cerrará la conexión.
 */
public class ChatCliente {
    public static void main(String[] args) {
        System.out.println("Cliente de chat iniciado...");
        try (Socket socket = new Socket("localhost", 2020);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

            Thread readThread = new Thread(() -> {
                try {
                    String response;
                    while ((response = in.readLine()) != null) {
                        System.out.println("Mensaje recibido: " + response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            readThread.start();

            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
