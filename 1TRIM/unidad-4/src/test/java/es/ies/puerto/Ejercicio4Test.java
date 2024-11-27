package es.ies.puerto;

import es.ies.puerto.ejercicio4.ServerChatMulti;
import org.junit.jupiter.api.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Ejercicio4Test {
    private static Thread serverThread;

    @BeforeAll
    public static void setUp() {
        serverThread = new Thread(() -> ServerChatMulti.main(new String[0]));
        serverThread.start();
    }

    @AfterAll
    public static void tearDown() throws InterruptedException {
        try (Socket clientSocket = new Socket("localhost", 2020);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            out.println("salir");
        } catch (Exception e) {
            e.printStackTrace();
        }
        serverThread.join();
    }

    @Test
    public void testClientServerCommunication() throws Exception {
        try (Socket clientSocket1 = new Socket("localhost", 2020);
             PrintWriter out1 = new PrintWriter(clientSocket1.getOutputStream(), true);
             BufferedReader in1 = new BufferedReader(new InputStreamReader(clientSocket1.getInputStream()));

             Socket clientSocket2 = new Socket("localhost", 2020);
             PrintWriter out2 = new PrintWriter(clientSocket2.getOutputStream(), true);
             BufferedReader in2 = new BufferedReader(new InputStreamReader(clientSocket2.getInputStream()))) {

            // Enviar mensajes desde ambos clientes
            out1.println("Hola desde Cliente 1");
            out2.println("Hola desde Cliente 2");

            // Crear un conjunto para almacenar los mensajes recibidos por ambos clientes
            Set<String> receivedMessages = new HashSet<>();

            // Leer los mensajes de ambos clientes
            receivedMessages.add(in1.readLine());
            receivedMessages.add(in2.readLine());

            // Verificar que ambos mensajes esperados están en el conjunto de mensajes recibidos
            assertTrue(receivedMessages.contains("Hola desde Cliente 1"));
            assertFalse(receivedMessages.contains("Hola desde Cliente 2"));

            // Enviar mensaje de salida para detener el servidor
            out1.println("salir");
        }
    }
}
