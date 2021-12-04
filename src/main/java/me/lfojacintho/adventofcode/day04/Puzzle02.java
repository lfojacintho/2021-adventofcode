package me.lfojacintho.adventofcode.day04;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Puzzle02 {

    public static void main(String[] args) throws IOException {
        final String inputFileName = "src/main/resources/input-day04.txt";

        final List<Integer> drawNumbers;
        final List<BingoBoard> boards = new LinkedList<>();
        try (final BufferedReader reader = Files.newBufferedReader(Paths.get(inputFileName))) {
            drawNumbers = Arrays.stream(reader.readLine().split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
            reader.skip(1);

            int lineCount = 0;
            List<String> lines = new ArrayList<>();
            while (reader.ready()) {
                lines.add(reader.readLine());
                lineCount++;
                if (lineCount == 5) {
                    boards.add(BingoBoard.parseBoard(lines));
                    lines.clear();
                    lineCount = 0;
                    reader.skip(1);
                }
            }
        }


        final Set<BingoBoard> remainingBoards = new HashSet<>(boards);
        for (Integer drawNumber : drawNumbers) {
            for (BingoBoard board : boards) {
                if (remainingBoards.contains(board) && board.checkNumber(drawNumber) && board.checkBingo()) {
                    if (remainingBoards.size() > 1) {
                        remainingBoards.remove(board);
                    } else {
                        board.printBoard();

                        final int sumOfUncheckedNumbers = board.sumOfUncheckedNumbers();
                        System.out.println("Sum of unchecked numbers: " + sumOfUncheckedNumbers);
                        System.out.println("Last draw number: " + drawNumber);
                        System.out.println("Sum of unchecked numbers * draw number: " + sumOfUncheckedNumbers * drawNumber);
                        return;
                    }
                }

            }
        }
    }
}
