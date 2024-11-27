package es.ies.puerto.ejercicio2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Extiende (ServidorContinua extend Servidor) el ejercicio anterior para que el cliente y el servidor puedan
 * mantener una conversación continua. El cliente podrá enviar varios mensajes al servidor, y el servidor devolverá
 * cada mensaje hasta que el cliente envíe el mensaje "salir", lo cual cerrará la conexión.
 */
public class ChatServer {

    public static void main(String[] args) throws IOException {
        int port = 2020;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Servidor escuchando en el puerto " + port);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado: " + clientSocket.getInetAddress());

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Recibido: " + message);
                out.println("Eco: " + message);
            }

            clientSocket.close();
        }


    }
}
