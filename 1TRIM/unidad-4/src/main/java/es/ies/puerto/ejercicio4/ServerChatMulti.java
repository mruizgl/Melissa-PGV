package es.ies.puerto.ejercicio4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ServerChatMulti {
    private static final Set<PrintWriter> clientWriters = ConcurrentHashMap.newKeySet();
    private static boolean running = true;

    public static void main(String[] args) {
        System.out.println("Servidor de chat iniciado...");
        try (ServerSocket serverSocket = new ServerSocket(2020)) {
            while (running) {
                new ClientHandler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void stopServer() {
        running = false;
    }

    private static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                clientWriters.add(out);

                String message;
                while ((message = in.readLine()) != null) {
                    if ("salir".equalsIgnoreCase(message)) {
                        ServerChatMulti.stopServer(); // Detiene el servidor al recibir "salir"
                        break;
                    }
                    System.out.println("Mensaje recibido: " + message);
                    sendMessageToAllClients(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                clientWriters.remove(out);
            }
        }

        private void sendMessageToAllClients(String message) {
            for (PrintWriter writer : clientWriters) {
                writer.println(message);
            }
        }
    }
}
