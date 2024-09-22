package es.ies.puerto;

public class Ejercicio2 {
    public static void main(String[] args) {
        try {
            String[] commands = { "ping -c 4 google.com", "ls", "echo 'Proceso finalizado'" };
            for (String command : commands) {
                ProcessBuilder processBuilder = new ProcessBuilder("sh", "-c", command);
                Process process = processBuilder.start();
                process.waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
