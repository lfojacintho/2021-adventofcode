package me.lfojacintho.adventofcode.day05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Puzzle01 {

    public static void main(String[] args) throws IOException {
        final String inputFileName = "src/main/resources/input-day05.txt";

        try (Stream<String> lineStream = Files.lines(Paths.get(inputFileName))) {
            final Pattern pattern = Pattern.compile("(\\d+),(\\d+) -> (\\d+),(\\d+)");

            final List<VentLine> ventLines = lineStream
                .map(line -> {
                    final Matcher matcher = pattern.matcher(line);
                    if (matcher.find()) {
                        final Coordinate firstCoordinate =
                            new Coordinate(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
                        final Coordinate secondCoordinate =
                            new Coordinate(Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)));
                        if (firstCoordinate.isHorizontallyAligned(secondCoordinate)
                            || firstCoordinate.isVerticallyAligned(secondCoordinate)) {
                            return new VentLine(firstCoordinate, secondCoordinate);
                        }
                    }

                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

            final Set<Coordinate> overlappingCoordinates = new HashSet<>();
            for (int i = 0; i < ventLines.size() - 1; i++) {
                for (int j = i+1; j < ventLines.size(); j++) {
                    overlappingCoordinates.addAll(ventLines.get(i).getOverlappingCoordinates(ventLines.get(j)));
                }
            }

            System.out.println("Number of overlapping coordinates: " + overlappingCoordinates.size());
        }
    }
}
