import java.util.Random;

public class Ejercicio4 {
    private static final int GOAL_LIMIT = 150;
    private static boolean snitchCaught = false;
    private static int team1Score = 0;
    private static int team2Score = 0;
    private static final Object lock = new Object();


    static class Chaser implements Runnable {
        private final String teamName;

        public Chaser(String teamName) {
            this.teamName = teamName;
        }

        @Override
        public void run() {
            Random random = new Random();
            while (!snitchCaught) {
                try {
                    int points = random.nextInt(10) + 10;
                    synchronized (lock) {
                        if (teamName.equals("Team 1")) {
                            team1Score += points;
                        } else {
                            team2Score += points;
                        }
                        System.out.println(teamName + " anota " + points + " puntos. Puntuación actual: "
                                + "Team 1: " + team1Score + " - Team 2: " + team2Score);
                    }

                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Clase que representa al buscador que trata de atrapar la Snitch dorada
    static class Seeker implements Runnable {
        private final String teamName;

        public Seeker(String teamName) {
            this.teamName = teamName;
        }

        @Override
        public void run() {
            Random random = new Random();
            try {
                int timeToCatch = random.nextInt(15000) + 5000;
                Thread.sleep(timeToCatch);

                synchronized (lock) {
                    if (!snitchCaught) {
                        snitchCaught = true;
                        System.out.println(teamName + " ha atrapado la Snitch Dorada!");
                        if (teamName.equals("Team 1")) {
                            team1Score += 150;
                        } else {
                            team2Score += 150;
                        }
                    }
                    System.out.println("Puntuación final: Team 1: " + team1Score + " - Team 2: " + team2Score);
                    if (team1Score > team2Score) {
                        System.out.println("¡Team 1 gana el partido!");
                    } else {
                        System.out.println("¡Team 2 gana el partido!");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread chaserTeam1 = new Thread(new Chaser("Team 1"));
        Thread chaserTeam2 = new Thread(new Chaser("Team 2"));
        Thread seekerTeam1 = new Thread(new Seeker("Team 1"));
        Thread seekerTeam2 = new Thread(new Seeker("Team 2"));


        chaserTeam1.start();
        chaserTeam2.start();
        seekerTeam1.start();
        seekerTeam2.start();


        try {
            seekerTeam1.join();
            seekerTeam2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("El partido de Quidditch ha terminado.");
    }
}
