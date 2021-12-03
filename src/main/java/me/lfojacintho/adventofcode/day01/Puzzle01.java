package me.lfojacintho.adventofcode.day01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Puzzle01 {

    public static void main(String[] args) throws IOException {
        final String inputFileName = "src/main/resources/input-day01.txt";

        final List<Integer> depths;
        try (Stream<String> depthStream = Files.lines(Paths.get(inputFileName))) {
            depths = depthStream
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        }

        int previousDepth = depths.get(0);
        int sumOfIncreases = 0;
        for (int i = 1; i < depths.size(); i++) {
            int curDepth = depths.get(i);
            if (curDepth > previousDepth) {
                sumOfIncreases++;
            }
            previousDepth = curDepth;
        }

        System.out.println("Sum of increases: " + sumOfIncreases);
    }
}
