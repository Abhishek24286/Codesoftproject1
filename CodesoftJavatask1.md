//Here is the java code for task number 1

       import java.util.Random;

       import java.util.Scanner;

       public class MindMystery {
       // Inner class: Player
        static class Player {
        private String name;
        private int highScore;

        public Player(String name) {
            this.name = name;
            this.highScore = 0;
        }

        public String getName() {
            return name;
        }

        public int getHighScore() {
            return highScore;
        }

        public void updateScore(int score) {
            if (score > highScore) {
                highScore = score;
            }
        }
    }

    // Inner class: GameEngine
    static class GameEngine {
        private Player player;
        private final int maxTries = 7;
        private final Scanner scanner = new Scanner(System.in);
        private final Random random = new Random();

        public GameEngine(Player player) {
            this.player = player;
        }

        public void play() {
            int target = random.nextInt(100) + 1;
            int attempts = 0;
            boolean success = false;

            System.out.println("\nI've selected a number between 1 and 100.");
            System.out.println("You have " + maxTries + " attempts to guess it.");

            while (attempts < maxTries) {
                System.out.print("Attempt #" + (attempts + 1) + " - Enter your guess: ");
                int guess;
                try {
                    guess = Integer.parseInt(scanner.nextLine());
                } catch (Exception e) {
                    System.out.println("Invalid input. Enter a number.");
                    continue;
                }

                attempts++;

                if (guess == target) {
                    System.out.println("Correct! You guessed the number in " + attempts + " attempt(s).");
                    success = true;
                    break;
                } else if (guess < target) {
                    System.out.println("Too low.");
                } else {
                    System.out.println("Too high.");
                }
            }

            int score = success ? (maxTries - attempts) * 10 + (100 - attempts * 5) : 0;

            if (!success) {
                System.out.println("You failed to guess the number. It was: " + target);
            }

            System.out.println("Your score this round: " + score);
            player.updateScore(score);
            System.out.println("Your highest score: " + player.getHighScore());
        }
    }

    // Main method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("         WELCOME TO MIND MYSTERY");
        System.out.println("========================================");

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        Player player = new Player(name);
        GameEngine game = new GameEngine(player);

        String again;
        do {
            game.play();
            System.out.print("Do you want to play again? (yes/no): ");
            again = scanner.nextLine().trim().toLowerCase();
        } while (again.equals("yes") || again.equals("y"));

        System.out.println("\nThank you for playing, " + player.getName() + ".");
    }
    }

