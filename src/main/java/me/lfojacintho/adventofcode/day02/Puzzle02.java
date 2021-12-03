package me.lfojacintho.adventofcode.day02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Puzzle02 {

    public static void main(String[] args) throws IOException {
        final String inputFileName = "src/main/resources/input-day02.txt";

        final List<Command> commands;
        try (Stream<String> lineStream = Files.lines(Paths.get(inputFileName))) {
            commands = lineStream
                .map(Command::parseCommand)
                .collect(Collectors.toList());
        }

        int horizontalPosition = 0;
        int depth = 0;
        int aim = 0;
        for (Command command : commands) {
            switch (command.getDirection()) {
                case FORWARD:
                    horizontalPosition += command.getUnit();
                    depth += aim * command.getUnit();
                    break;
                case UP:
                    aim -= command.getUnit();
                    break;
                case DOWN:
                    aim += command.getUnit();
                    break;
            }
        }

        System.out.println("Horizontal Position: " + horizontalPosition);
        System.out.println("Depth: " + depth);
        System.out.println("Multiplication: " + horizontalPosition * depth);
    }
}
