package me.lfojacintho.adventofcode.day04;

import java.util.List;

public class BingoBoard {
    private int[][] numbers;
    private boolean[][] checked;

    private BingoBoard(final int boardSize) {
        numbers = new int[boardSize][boardSize];
        checked = new boolean[boardSize][boardSize];
    }

    public boolean checkNumber(final int number) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[i].length; j++) {
                if (numbers[i][j] == number) {
                    checked[i][j] = true;
                    return true;
                }
            }
        }

        return false;
    }

    public boolean checkBingo() {
        for (int i = 0; i < checked.length; i++) {
            boolean isWinner = true;
            for (int j = 0; j < checked.length; j++) {
                isWinner &= checked[i][j];
            }

            if (isWinner)
                return true;

            isWinner = true;
            for (int j = 0; j < checked.length; j++) {
                isWinner &= checked[j][i];
            }

            if (isWinner)
                return true;
        }

        return false;
    }

    public int sumOfUncheckedNumbers() {
        int sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[i].length; j++) {
                if (!checked[i][j])
                    sum += numbers[i][j];
            }
        }

        return sum;
    }

    public void printBoard() {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[i].length; j++) {
                System.out.printf("%2d%s ", numbers[i][j], checked[i][j] ? "*" : " ");
            }
            System.out.println();
        }
    }

    public static BingoBoard parseBoard(final List<String> lines) {
        final BingoBoard board = new BingoBoard(lines.size());

        for (int i = 0; i < lines.size(); i++) {
            final String sanitizedLine = sanitizeLine(lines.get(i));
            final String[] columns = sanitizedLine.split(" ");
            for (int j = 0; j < columns.length; j++) {
                board.numbers[i][j] = Integer.parseInt(columns[j]);
                board.checked[i][j] = false;
            }
        }

        return board;
    }

    private static String sanitizeLine(final String line) {
        return line.trim().replaceAll(" +", " ");
    }
}
