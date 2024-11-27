package es.ies.puerto;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Ejercicio3Test {
    private static final int PUERTO = 2020;
    private Thread hiloServidor;

    @BeforeEach
    void iniciarServidor() {
        // Iniciar el servidor en un nuevo hilo
        hiloServidor = new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
                System.out.println("Servidor escuchando en el puerto " + PUERTO);
                while (true) {
                    Socket socket = serverSocket.accept();
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
            } catch (IOException e) {
                System.out.println("Error en el servidor: " + e.getMessage());
            }
        });
        hiloServidor.start();
    }

    @AfterEach
    void detenerServidor() throws InterruptedException {
        // Detener el hilo del servidor después de cada prueba
        if (hiloServidor != null) {
            hiloServidor.join();
        }
    }

    @Test
    void pruebaSumaNumeros() throws IOException {
        // Conectar el cliente al servidor y enviar dos números
        try (Socket socketCliente = new Socket("localhost", PUERTO);
             PrintWriter out = new PrintWriter(socketCliente.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()))) {

            // Enviar dos números separados por un espacio
            String mensajeEnviado = "3 5";
            out.println(mensajeEnviado);

            // Leer la respuesta del servidor y verificar la suma
            String respuestaRecibida = in.readLine();
            Assertions.assertEquals("8", respuestaRecibida, "La suma debe ser correcta para los números enviados.");
        }
    }
}
