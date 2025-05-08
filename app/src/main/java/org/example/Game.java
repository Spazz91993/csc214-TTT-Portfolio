package org.example;

import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Game {
    private Interface board;
    private Player playerX;
    private Player playerO;
    private boolean computerMode;
    private OpportunisticComputer computer;
    private boolean computerPlaysX;
    private int xWins = 0;
    private int oWins = 0;
    private int ties = 0;
    private Player currentPlayer;
    Scanner scanner = new Scanner(System.in);

    public Game(String playerXName, String playerOName) {
        board = new Interface();
        playerX = new Player('X', playerXName);
        playerO = new Player('O', playerOName);
        currentPlayer = playerX;
        computerMode = false;
    }

    public Game(String humanName, boolean humanFirst) {
        board = new Interface();
        computerMode = true;
        if (humanFirst) {
            playerX = new Player('X', humanName);
            playerO = new Player('O', "Computer");
            computer = new OpportunisticComputer('O');
            computerPlaysX = false;
        } else {
            playerX = new Player('X', "Computer");
            playerO = new Player('O', humanName);
            computer = new OpportunisticComputer('X');
            computerPlaysX = true;
        }
        currentPlayer = playerX;
    }

    public void play() {
        boolean rematch = true;
        while (rematch) {
            boolean gameEnd = false;
            int turnCount = 0;
            Interface.welcomeMsg();
            while (!gameEnd && turnCount < 9) {
                board.displayBoard();
                int choice;

                if (computerMode && currentPlayer.getName().equals("Computer")) {
                    choice = computer.makeMove(board.sQ, turnCount);
                    System.out.println("Computer chooses square: " + choice);
                } else {
                    System.out.println(currentPlayer.getName() + ", it's your turn. Choose a square (1-9): ");
                    choice = board.inputValidation(scanner);
                }

                board.makeMove(choice, currentPlayer.getSymbol());

                if (board.isWinner()) {
                    board.displayBoard();
                    System.out.println("Player " + currentPlayer.getName() + " wins!");
                    if (currentPlayer == playerX) xWins++;
                    else oWins++;
                    gameEnd = true;
                } else {
                    turnCount++;
                    if (turnCount < 9) {
                        currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
                    }
                }
            }

            if (!gameEnd) {
                board.displayBoard();
                System.out.println("It's a draw!");
                ties++;
            }

            printStats();
            rematch = board.playAgain(scanner);

            if (rematch) {
                if (gameEnd) {
                    currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
                } else {
                    currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
                }
            } else {
                writeLogToFile();
            }
        }
        scanner.close();
    }

    private void printStats() {
        System.out.println("===================================");
        System.out.println(" Current Game Stats:");
        System.out.println(" " + playerX.getName() + " Wins: " + xWins);
        System.out.println(" " + playerO.getName() + " Wins: " + oWins);
        System.out.println(" Ties: " + ties);
        System.out.println("===================================");
    }

    private void writeLogToFile() {
        try (FileWriter writer = new FileWriter("game_log.txt")) {
            writer.write("Tic-Tac-Toe Game Log\n");
            writer.write("====================\n");
            writer.write("Player X (" + playerX.getName() + ") Wins: " + xWins + "\n");
            writer.write("Player O (" + playerO.getName() + ") Wins: " + oWins + "\n");
            writer.write("Ties: " + ties + "\n");
            writer.write("====================\n");
            System.out.println("Game log saved to game_log.txt.");
        } catch (IOException e) {
            System.out.println("Error writing to game log: " + e.getMessage());
        }
    }
}
