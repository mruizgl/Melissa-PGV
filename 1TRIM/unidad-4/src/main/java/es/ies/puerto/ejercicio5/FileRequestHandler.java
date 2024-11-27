package es.ies.puerto.ejercicio5;

import java.io.*;
import java.net.Socket;

public class FileRequestHandler implements Runnable{

    private static final String FILES_DIRECTORY = "src/main/resources/ficheros/";
    private Socket clientSocket;

    public FileRequestHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             OutputStream out = clientSocket.getOutputStream()) {

            String fileName = in.readLine();
            System.out.println("Solicitando archivo: " + fileName);
            sendFile(fileName, out);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendFile(String fileName, OutputStream out) {
        File file = new File(FILES_DIRECTORY + fileName);
        if (!file.exists()) {
            System.out.println("Archivo no encontrado en el servidor: " + fileName);
            return;
        }

        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            out.flush();
            System.out.println("Archivo enviado: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
