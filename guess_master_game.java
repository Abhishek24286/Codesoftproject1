import java.util.Scanner;
import java.util.Random;

public class GuessMaster {

    // Player profile with scoring logic
    static class UserProfile {
        private final String username;
        private int highestScore;

        public UserProfile(String username) {
            this.username = username;
            this.highestScore = 0;
        }

        public void updateHighScore(int newScore) {
            if (newScore > highestScore) {
                highestScore = newScore;
            }
        }

        public String getUsername() {
            return username;
        }

        public int getHighestScore() {
            return highestScore;
        }
    }

    // Main game logic controller
    static class GameSession {
        private static final int MAX_ATTEMPTS = 7;
        private final Scanner scanner;
        private final Random random;
        private final UserProfile player;

        public GameSession(UserProfile player) {
            this.player = player;
            this.scanner = new Scanner(System.in);
            this.random = new Random();
        }

        public void start() {
            int secretNumber = random.nextInt(100) + 1;
            int attemptsUsed = 0;
            boolean isGuessed = false;

            System.out.println("\nA number between 1 and 100 has been chosen. Can you guess it?");
            System.out.println("You have " + MAX_ATTEMPTS + " attempts. Good luck!");

            while (attemptsUsed < MAX_ATTEMPTS) {
                System.out.print("Guess #" + (attemptsUsed + 1) + ": ");
                int userGuess = getValidInput();

                attemptsUsed++;

                if (userGuess == secretNumber) {
                    System.out.println("\uD83C\uDF89 Spot on! You cracked it in " + attemptsUsed + " tries.");
                    isGuessed = true;
                    break;
                } else if (userGuess < secretNumber) {
                    System.out.println("\u2B06 Too low!");
                } else {
                    System.out.println("\u2B07 Too high!");
                }
            }

            int score = isGuessed ? calculateScore(attemptsUsed) : 0;
            if (!isGuessed) {
                System.out.println("\uD83D\uDCA1 The number was: " + secretNumber);
            }

            System.out.println("Score this round: " + score);
            player.updateHighScore(score);
            System.out.println("\uD83C\uDFC5 Highest Score: " + player.getHighestScore());
        }

        private int getValidInput() {
            while (true) {
                try {
                    return Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.print("Invalid number. Try again: ");
                }
            }
        }

        private int calculateScore(int attempts) {
            return (MAX_ATTEMPTS - attempts) * 15 + 50;
        }
    }

    // Application entry point
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("         \uD83E\uDDE0 Welcome to GuessMaster");
        System.out.println("========================================");

        System.out.print("Enter your name: ");
        String name = input.nextLine();

        UserProfile player = new UserProfile(name);
        GameSession session = new GameSession(player);

        String choice;
        do {
            session.start();
            System.out.print("Play again? (yes/no): ");
            choice = input.nextLine().trim().toLowerCase();
        } while (choice.equals("yes") || choice.equals("y"));

        System.out.println("\uD83D\uDC4B Thanks for playing, " + player.getUsername() + "!");
        input.close();
    }
}
