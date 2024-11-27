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

public class Ejercicio2Test {
    private static final int PUERTO = 2020;
    private Thread hiloServidor;

    @BeforeEach
    void iniciarServidor() {
        // Iniciar el servidor en un nuevo hilo
        hiloServidor = new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
                System.out.println("Servidor escuchando en el puerto " + PUERTO);
                Socket clientSocket = serverSocket.accept();

                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String mensaje;
                while ((mensaje = in.readLine()) != null) {
                    System.out.println("Recibido: " + mensaje);
                    if (mensaje.equalsIgnoreCase("salir")) {
                        break;
                    }
                    out.println("Eco: " + mensaje);
                }
                clientSocket.close();
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
    void pruebaConversacionContinua() throws IOException {
        try (Socket socketCliente = new Socket("localhost", PUERTO);
             PrintWriter out = new PrintWriter(socketCliente.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()))) {

            // Enviar varios mensajes al servidor y verificar la respuesta de cada uno
            String[] mensajesEnviados = {"Hola", "¿Cómo estás?", "salir"};
            String[] respuestasEsperadas = {"Eco: Hola", "Eco: ¿Cómo estás?"};

            for (int i = 0; i < mensajesEnviados.length - 1; i++) {
                out.println(mensajesEnviados[i]);
                String respuestaRecibida = in.readLine();
                Assertions.assertEquals(respuestasEsperadas[i], respuestaRecibida, "La respuesta del servidor debe coincidir con el eco del mensaje enviado.");
            }

            // Enviar "salir" para finalizar la conversación
            out.println(mensajesEnviados[mensajesEnviados.length - 1]);
            // Confirmar que el servidor no responde después de "salir"
            String finDeConexion = in.readLine();
            Assertions.assertNull(finDeConexion, "La conexión debe cerrarse después de enviar 'salir'.");
        }
    }
}
