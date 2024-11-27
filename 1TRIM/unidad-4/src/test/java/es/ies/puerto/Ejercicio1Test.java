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

public class Ejercicio1Test {
    private static final int PUERTO = 12345;
    private Thread hiloServidor;

    @BeforeEach
    void iniciarServidor() {
        // Iniciar el servidor en un nuevo hilo
        hiloServidor = new Thread(() -> {
            try (ServerSocket servidorSocket = new ServerSocket(PUERTO)) {
                System.out.println("Servidor escuchando en el puerto " + PUERTO);
                Socket socketCliente = servidorSocket.accept(); // Espera conexión de cliente

                // Configurar entrada y salida
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
                PrintWriter salida = new PrintWriter(socketCliente.getOutputStream(), true);

                // Leer y responder el mensaje
                String mensaje = entrada.readLine();
                salida.println(mensaje);

                socketCliente.close();
            } catch (IOException e) {
                System.out.println("Error en el servidor: " + e.getMessage());
            }
        });
        hiloServidor.start();
    }

    @AfterEach
    void detenerServidor() throws InterruptedException {
        // Detiene el hilo del servidor después de cada prueba
        if (hiloServidor != null) {
            hiloServidor.join(); // Espera a que el hilo termine
        }
    }

    @Test
    void pruebaComunicacionClienteServidor() throws IOException {
        // Crear cliente y conectar al servidor
        try (Socket socketCliente = new Socket("localhost", PUERTO);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
             PrintWriter salida = new PrintWriter(socketCliente.getOutputStream(), true)) {

            String mensajeEnviado = "Hola, servidor!";
            salida.println(mensajeEnviado); // Enviar mensaje al servidor

            // Leer la respuesta del servidor
            String mensajeRecibido = entrada.readLine();
            Assertions.assertEquals(mensajeEnviado, mensajeRecibido, "El mensaje recibido debe ser igual al mensaje enviado.");
        }
    }
}
