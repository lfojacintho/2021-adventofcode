package me.lfojacintho.adventofcode.day02;

public enum Direction {
    FORWARD,
    UP,
    DOWN;

    static Direction parseDirection(final String dir) {
        if (dir == null) {
            throw new IllegalArgumentException("Direction must not be null");
        }

        for (Direction direction : Direction.values()) {
            if (direction.name().equalsIgnoreCase(dir)) {
                return direction;
            }
        }

        throw new IllegalArgumentException("Direction not supported");
    }
}
