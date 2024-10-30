package es.ies.puerto.ejercicio3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Diseña un servidor que reciba dos números enteros de un cliente, los sume y devuelva el resultado. El cliente
 * deberá enviar los dos números separados por un espacio. Por ejemplo, si envía 5 7, el servidor deberá responder 12.
 */
public class ChatServerNum {
    public static void main(String[] args) throws IOException {
        int puerto = 2020;
        ServerSocket serverSocket = new ServerSocket(puerto);
        System.out.println("Server escuchando en " + puerto);

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Cliente conectado: " + socket.getInetAddress());

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String inputLine = in.readLine();
            String[] numbers = inputLine.split(" ");

            int num1 = Integer.parseInt(numbers[0]);
            int num2 = Integer.parseInt(numbers[1]);

            int sum = num1 + num2;

            out.println(sum);

            socket.close();
        }
    }
}
