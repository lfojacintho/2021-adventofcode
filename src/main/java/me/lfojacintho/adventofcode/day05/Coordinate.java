package me.lfojacintho.adventofcode.day05;

import java.util.Objects;

public class Coordinate {
    private final int x;
    private final int y;

    public Coordinate(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isHorizontallyAligned(final Coordinate other) {
        return this.x == other.x;
    }

    public boolean isVerticallyAligned(final Coordinate other) {
        return this.y == other.y;
    }

    public boolean isDiagonallyAligned(final Coordinate other) {
        return Math.abs(this.x - other.x) == Math.abs(this.y - other.y);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Coordinate that = (Coordinate) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
