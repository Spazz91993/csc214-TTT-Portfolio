package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OpportunisticComputer {
    private char symbol;
    private char opponent;
    private Random random = new Random();

    public OpportunisticComputer(char symbol) {
        this.symbol = symbol;
        this.opponent = (symbol == 'X') ? 'O' : 'X';
    }

    public int makeMove(char[] sQ, int turnCount) {
        if (turnCount == 0) {
            int[] corners = {1, 3, 7, 9};
            return corners[random.nextInt(4)];
        }
        if (turnCount == 1 && sQ[4] != 'X' && sQ[4] != 'O') {
            return 5;
        }
        for (int i = 0; i < 9; i++) {
            if (sQ[i] != 'X' && sQ[i] != 'O') {
                char temp = sQ[i];
                sQ[i] = symbol;
                if (isWinner(sQ)) {
                    sQ[i] = temp;
                    return i + 1;
                }
                sQ[i] = temp;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (sQ[i] != 'X' && sQ[i] != 'O') {
                char temp = sQ[i];
                sQ[i] = opponent;
                if (isWinner(sQ)) {
                    sQ[i] = temp;
                    return i + 1;
                }
                sQ[i] = temp;
            }
        }
        List<Integer> available = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (sQ[i] != 'X' && sQ[i] != 'O') {
                available.add(i + 1);
            }
        }
        return available.get(random.nextInt(available.size()));
    }

    private boolean isWinner(char[] sQ) {
        int[][] winConditions = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
        };
        for (int[] condition : winConditions) {
            if (sQ[condition[0]] == sQ[condition[1]] &&
                sQ[condition[1]] == sQ[condition[2]]) {
                return true;
            }
        }
        return false;
    }
}
