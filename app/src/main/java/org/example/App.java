package org.example;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.println("Choose game mode:");
        System.out.println("1. Human vs Human");
        System.out.println("2. Human vs Computer");
        int mode = 0;

        while (mode != 1 && mode != 2) {
            System.out.print("Enter 1 or 2: ");
            if (scanner.hasNextInt()) {
                mode = scanner.nextInt();
                scanner.nextLine();
            } else {
                scanner.nextLine();
            }
        }

        if (mode == 1) {
            System.out.print("Enter name for Player X: ");
            String playerXName = scanner.nextLine();
            System.out.print("Enter name for Player O: ");
            String playerOName = scanner.nextLine();
            Game game = new Game(playerXName, playerOName);
            game.play();
        } else {
            System.out.print("Enter your name: ");
            String humanName = scanner.nextLine();
            System.out.print("Type 'first' to go first, or any other input to go second: ");
            String goesFirst = scanner.nextLine().trim().toLowerCase();
            boolean humanFirst = goesFirst.equals("first");
            Game game = new Game(humanName, humanFirst);
            game.play();
        }

        scanner.close();
    }
}
