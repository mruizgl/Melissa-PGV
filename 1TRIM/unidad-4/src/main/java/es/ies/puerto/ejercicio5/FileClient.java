package es.ies.puerto.ejercicio5;

import java.io.*;
import java.net.Socket;
import java.util.Random;

/**
 * Extiende (ServidorContinua extend Servidor) el ejercicio anterior para que el cliente y el servidor puedan mantener una conversación continua. El cliente podrá enviar varios mensajes al servidor, y el servidor devolverá cada mensaje hasta que el cliente envíe el mensaje "salir", lo cual cerrará la conexión.
 *
 * En este caso debenos de manejar conexiones continuas y gestionar la terminación de la comunicación.
 *
 * Ten en cuenta que debes de:
 *
 * Usa un bucle while en el servidor para mantener la conexión abierta.
 * Implementa una condición de parada para finalizar el chat.
 */
public class FileClient {
    public static void main(String[] args) throws IOException {
        String serverAddress = "localhost";
        int port = 12345;

        try (Socket socket = new Socket(serverAddress, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             InputStream in = socket.getInputStream()) {

            Random random = new Random();
            int fileNumber = random.nextInt(20) + 1;
            String fileName = "fichero" + fileNumber + ".txt";
            out.println(fileName);


            File receivedFile = new File("received_" + fileName);
            try (FileOutputStream fos = new FileOutputStream(receivedFile)) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }
                fos.flush();
                System.out.println("Archivo recibido: " + receivedFile.getName());


                showFileContent(receivedFile);

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void showFileContent(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            System.out.println("Contenido del archivo:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + file.getName());
            e.printStackTrace();
        }
    }
}
