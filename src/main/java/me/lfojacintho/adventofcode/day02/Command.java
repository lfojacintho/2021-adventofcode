package me.lfojacintho.adventofcode.day02;

public class Command {

    private final Direction direction;
    private final int unit;

    private Command(final Direction direction, final int unit) {
        this.direction = direction;
        this.unit = unit;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getUnit() {
        return unit;
    }

    public static Command parseCommand(final String line) {
        final String[] words = line.split(" ");
        return new Command(Direction.parseDirection(words[0]), Integer.parseInt(words[1]));
    }
}
