package me.lfojacintho.adventofcode.day05;

import java.util.HashSet;
import java.util.Set;

public class VentLine {

    protected final Set<Coordinate> coordinates;

    public VentLine(final Coordinate start, final Coordinate end) {
        this.coordinates = getLine(start, end);
    }

    public Set<Coordinate> getCoordinates() {
        return coordinates;
    }

    public Set<Coordinate> getOverlappingCoordinates(final VentLine other) {
        final Set<Coordinate> overlappingCoordinates = new HashSet<>();
        for (Coordinate coordinate : other.getCoordinates()) {
            if (coordinates.contains(coordinate)) {
                overlappingCoordinates.add(coordinate);
            }
        }

        return overlappingCoordinates;
    }

    protected Set<Coordinate> getLine(final Coordinate start, final Coordinate end) {
        final Set<Coordinate> coords = new HashSet<>();
        coords.add(start);
        coords.add(end);
        if (start.getX() == end.getX()) {
            final int minY = Math.min(start.getY(), end.getY());
            final int maxY = Math.max(start.getY(), end.getY());
            for (int y = minY + 1; y < maxY; y++) {
                coords.add(new Coordinate(start.getX(), y));
            }
        } else if (start.getY() == end.getY()) {
            final int minX = Math.min(start.getX(), end.getX());
            final int maxX = Math.max(start.getX(), end.getX());
            for (int x = minX; x < maxX; x++) {
                coords.add(new Coordinate(x, start.getY()));
            }
        } else {
            throw new IllegalArgumentException("Coordinates must be on the same line");
        }
        return coords;
    }
}
