package es.ies.puerto.ejercicio5;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {
    public static void main(String[] args) throws IOException {
        int port = 12345;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor de archivos iniciado en el puerto " + port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new FileRequestHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



