package me.lfojacintho.adventofcode.day03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Puzzle02 {

    public static void main(String[] args) throws IOException {
        final String inputFileName = "src/main/resources/input-day03.txt";

        final List<String> binaryValues;
        try (Stream<String> lineStream = Files.lines(Paths.get(inputFileName))) {
            binaryValues = lineStream.collect(Collectors.toList());
        }

        final List<String> oxygenGenValues = new ArrayList<>(binaryValues);
        int position = 0;
        while (oxygenGenValues.size() > 1) {
            final int mostFrequentBit = findMostFrequentBit(oxygenGenValues, position);
            final int finalPosition = position;
            oxygenGenValues.removeIf(value -> Character.getNumericValue(value.charAt(finalPosition)) != mostFrequentBit);
            position++;
        }

        final List<String> co2ScrubberValues = new ArrayList<>(binaryValues);
        position = 0;
        while (co2ScrubberValues.size() > 1) {
            final int lessFrequentBit = findLessFrequentBit(co2ScrubberValues, position);
            final int finalPosition = position;
            co2ScrubberValues.removeIf(value -> Character.getNumericValue(value.charAt(finalPosition)) != lessFrequentBit);
            position++;
        }

        final int oxygenGeneratorRating = Integer.parseInt(oxygenGenValues.get(0), 2);
        final int co2ScrubberRating = Integer.parseInt(co2ScrubberValues.get(0), 2);
        System.out.println("Oxygen Generator Rating: " + oxygenGeneratorRating);
        System.out.println("CO2 Scrubber Rating: " + co2ScrubberRating);
        System.out.println("Multiplication: " + oxygenGeneratorRating * co2ScrubberRating);
    }

    private static int findMostFrequentBit(final List<String> binaryNumbers, final int position) {
        final int[] bitFrequency = buildFrequencyMap(binaryNumbers, position);

        if (bitFrequency[1] >= bitFrequency[0]) {
            return 1;
        } else {
            return 0;
        }
    }

    private static int findLessFrequentBit(final List<String> binaryNumbers, final int position) {
        final int[] bitFrequency = buildFrequencyMap(binaryNumbers, position);

        if (bitFrequency[0] <= bitFrequency[1]) {
            return 0;
        } else {
            return 1;
        }
    }

    private static int[] buildFrequencyMap(final List<String> binaryNumbers, final int position) {
        final int[] bitFrequency = new int[2];
        for (String binaryNumber : binaryNumbers) {
            final int bitValue = Character.getNumericValue(binaryNumber.charAt(position));
            bitFrequency[bitValue]++;
        }
        return bitFrequency;
    }
}
