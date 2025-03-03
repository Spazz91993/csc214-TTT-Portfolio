package org.example;
import java.util.Scanner;

public class Interface {
    public char[] sQ;
    private Scanner scanner;
    public Interface() {
        sQ = new char[9];
        resetBoard();
        scanner = new Scanner(System.in);
    }

    public void resetBoard() {
        for (int i = 0; i < 9; ++i) {
            sQ[i] = (char) ('1' + i);
        }
    }

    public void displayBoard() {
        System.out.println("\t\t" + sQ[0] + "\t" + "|" + "\t" + sQ[1] + "\t" + "|" + "\t" + sQ[2]);
        System.out.println("\t----------------+---------------+----------------");
        System.out.println("\t\t" + sQ[3] + "\t" + "|" + "\t" + sQ[4] + "\t" + "|" + "\t" + sQ[5]);
        System.out.println("\t----------------+---------------+----------------");
        System.out.println("\t\t" + sQ[6] + "\t" + "|" + "\t" + sQ[7] + "\t" + "|" + "\t" + sQ[8]);
    }

    public int getPlayerInput() {
        int choice = -1;
        while (choice == -1) {
            System.out.println("Choose a square (1-9): ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (!isValidMove(choice)) {
                    choice = -1;
                }
            }
            else {
                scanner.next();
                System.out.println("Invalid input. Please enter a valid number between 1 and 9.");
            }
        }
        return choice;
    }

    public boolean isValidMove(int choice) {
        if (choice < 1 || choice > 9) {
            System.out.println("Invalid input. Please choose a square between 1 and 9.");
            return false;
        }
        if (sQ[choice - 1] == 'X' || sQ[choice - 1] == 'O') {
            System.out.println("This square is already taken. Please choose another square, silly.");
            return false;
        }
        return true;
    }

    public void makeMove(int choice, char player) {
        sQ[choice - 1] = player;
    }

    public boolean isWinner() {
        int[][] winConditions = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}    
        };

        for (int[] condition : winConditions) {
            if (sQ[condition[0]] == sQ[condition[1]] && sQ[condition[1]] == sQ[condition[2]] && sQ[condition[0]] != ' ') {
                return true;
            }
        }
        return false;
    }

    public boolean playAgain() {
        System.out.println("Do you want to play again? (yes/no): ");
        String userInput = scanner.next().trim().toLowerCase();
        if (userInput.equals("yes")){
            resetBoard();
            System.out.println("Starting the next adventure...");
            return true;
        }
        else if (userInput.equals("no")){
            System.out.println("Have a beautiful day.");
            scanner.close();
            return false;
        }
        else {
            System.out.println("Invalid response. Please enter 'yes' or 'no'.");
            return playAgain();
        }
    }

    public static void welcomeMsg() {
        System.out.println("                                                          ");
        System.out.println(" _ _ _ _____ __    _____ _____ _____ _____    _____ _____ ");
        System.out.println("| | | |   __|  |  |     |     |     |   __|  |_   _|     |");
        System.out.println("| | | |   __|  |__|   --|  |  | | | |   __|    | | |  |  |");
        System.out.println("|_____|_____|_____|_____|_____|_|_|_|_____|    |_| |_____|");
        System.out.println(" _________  ___  ________          _________  ________  ________     ");
        System.out.println("|\\___   ___\\\\  \\|\\   ____\\        |\\___   ___\\\\   __  \\|\\   ____\\    ");
        System.out.println("\\|___ \\  \\_\\ \\  \\ \\  \\___|        \\|___ \\  \\_\\ \\  \\|\\  \\ \\  \\___|    ");
        System.out.println("     \\ \\  \\ \\ \\  \\ \\  \\                \\ \\  \\ \\ \\   __  \\ \\  \\       ");
        System.out.println("      \\ \\  \\ \\ \\  \\ \\  \\____            \\ \\  \\ \\ \\  \\ \\  \\ \\  \\____  ");
        System.out.println("       \\ \\__\\ \\ \\__\\ \\_______\\           \\ \\__\\ \\ \\__\\ \\__\\ \\_______\\ ");
        System.out.println("        \\|__|  \\|__|\\|_______|            \\|__|  \\|__|\\|__|\\|_______|");
        System.out.println("                                                                     ");
        System.out.println("                                                                     ");
        System.out.println("                    _________  ________  _______   ___               ");
        System.out.println("                   |\\___   ___\\\\   __  \\|\\  ___ \\ |\\  \\              ");
        System.out.println("                   \\|___ \\  \\_\\ \\  \\|\\  \\ \\   __/|\\ \\  \\             ");
        System.out.println("                        \\ \\  \\ \\ \\  \\\\\\  \\ \\  \\_|/_\\ \\  \\            ");
        System.out.println("                         \\ \\  \\ \\ \\  \\\\\\  \\ \\  \\_|\\ \\ \\__\\           ");
        System.out.println("                          \\ \\__\\ \\ \\_______\\ \\_______\\|__|           ");
        System.out.println("                           \\|__|  \\|_______|\\|_______|   ___         ");
        System.out.println("                                                        |\\__\\        ");
        System.out.println("                                                        \\|__|        ");
        System.out.println("                                                                     ");
    }
}
