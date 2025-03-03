package org.example;

public class Game {
    private Interface board;
    private Player playerX;
    private Player playerO;

    public Game(String playerXName, String playerOName) {
        board = new Interface();
        playerX = new Player('X', playerXName);
        playerO = new Player('O', playerOName);
    }

    public void play() {
        Player currentPlayer = playerX;
        boolean rematch = true;
        while(rematch){
            boolean gameEnd = false;
            int turnCount = 0;
            Interface.welcomeMsg();
            while (!gameEnd && turnCount < 9) {
                board.displayBoard();
                System.out.println(currentPlayer.getName() + ", it's your turn. Choose a square (1-9): ");
                int choice = board.getPlayerInput();
                board.makeMove(choice, currentPlayer.getSymbol());
                if (board.isWinner()) {
                    board.displayBoard();
                    System.out.println("Player " + currentPlayer.getName() + " wins!");
                    gameEnd = true;
                }
                else {
                    turnCount++;
                    currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
                }
            }
            
            if (!gameEnd) {
                board.displayBoard();
                System.out.println("It's a draw!");
            }
            rematch = board.playAgain();
        }
    }
}