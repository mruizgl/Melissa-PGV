package es.ies.puerto;

import java.io.InputStream;
import java.io.OutputStream;

public class Ejercicio10 {
    public static void main(String[] args) {
        try {
            ProcessBuilder processBuilder1 = new ProcessBuilder("ls");
            ProcessBuilder processBuilder2 = new ProcessBuilder("grep", "java");

            Process proc1 = processBuilder1.start();
            Process proc2 = processBuilder2.start();

            InputStream in = proc1.getInputStream();
            OutputStream out = proc2.getOutputStream();

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }

            proc1.waitFor();
            proc2.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
