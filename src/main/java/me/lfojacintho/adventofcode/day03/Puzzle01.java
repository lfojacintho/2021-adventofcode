package me.lfojacintho.adventofcode.day03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Puzzle01 {

    public static void main(String[] args) throws IOException {
        final String inputFileName = "src/main/resources/input-day03.txt";

        final List<String> binaryValues;
        try (Stream<String> lineStream = Files.lines(Paths.get(inputFileName))) {
            binaryValues = lineStream.collect(Collectors.toList());
        }

        final int binaryNumbersLength = binaryValues.get(0).length();
        final int[][] frequencyMap = new int[binaryNumbersLength][2];
        for (String binaryValue : binaryValues) {
            for (int i = 0; i < binaryValue.length(); i++) {
                final int bitValue = Character.getNumericValue(binaryValue.charAt(i));
                frequencyMap[i][bitValue]++;
            }
        }

        final StringBuilder gammaRateBuilder = new StringBuilder();
        final StringBuilder epsilonRateBuilder = new StringBuilder();
        for (int i = 0; i < binaryNumbersLength; i++) {
            if (frequencyMap[i][0] >= frequencyMap[i][1]) {
                gammaRateBuilder.append('0');
                epsilonRateBuilder.append('1');
            } else {
                gammaRateBuilder.append('1');
                epsilonRateBuilder.append('0');
            }
        }

        final int gammaRate = Integer.parseInt(gammaRateBuilder.toString(), 2);
        final int epsilonRate = Integer.parseInt(epsilonRateBuilder.toString(), 2);

        System.out.println("Gamma Rate: " + gammaRate);
        System.out.println("Epsilon Rate: " + epsilonRate);
        System.out.println("Multiplication: " + gammaRate * epsilonRate);
    }
}
