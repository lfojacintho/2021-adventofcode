package me.lfojacintho.adventofcode.day04;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Puzzle01 {

    public static void main(String[] args) throws IOException {
        final String inputFileName = "src/main/resources/input-day04.txt";

        final BufferedReader reader = Files.newBufferedReader(Paths.get(inputFileName));
        final List<Integer> drawNumbers = Arrays.stream(reader.readLine().split(","))
            .map(Integer::parseInt)
            .collect(Collectors.toList());
        reader.readLine();

        int lineCount = 0;
        List<String> lines = new ArrayList<>();
        final List<BingoBoard> boards = new ArrayList<>();
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

        for (Integer drawNumber : drawNumbers) {
            for (BingoBoard board : boards) {
                if (board.checkNumber(drawNumber) && board.checkBingo()) {
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
