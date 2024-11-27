package es.ies.puerto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Ejercicio6 {
    public boolean startWorkers(int numInstances, String executable, String classPath, String className) {

        List<Integer> result = new ArrayList<>();
        for (int i=1; i <= numInstances; i++) {
            try {
                ProcessBuilder processBuilder = new ProcessBuilder(executable, "-cp", classPath, className,
                        String.valueOf(i));

                Process process = processBuilder.start();

                int exitCode = process.waitFor();
                result.add(exitCode);


            } catch (IOException | InterruptedException e) {
                return false;
            }
        }

        int counter = 0;
        for (Integer exitCode : result) {
            if (exitCode == 0) {
                counter++;
            }
        }
        return result.size() == counter;

    }
}
