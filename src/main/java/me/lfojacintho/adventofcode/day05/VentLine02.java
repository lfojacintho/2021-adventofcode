package me.lfojacintho.adventofcode.day05;

import java.util.HashSet;
import java.util.Set;

public class VentLine02 extends VentLine {

    public VentLine02(final Coordinate start, final Coordinate end) {
        super(start, end);
    }

    @Override
    protected Set<Coordinate> getLine(final Coordinate start, final Coordinate end) {
        final Set<Coordinate> coords = new HashSet<>();
        coords.add(start);
        coords.add(end);

        final int xMovingFactor = getMovingFactor(start.getX(), end.getX());
        final int yMovingFactor = getMovingFactor(start.getY(), end.getY());

        Coordinate coord = start;
        while (!coord.equals(end)) {
            coord = new Coordinate(coord.getX() + xMovingFactor, coord.getY() + yMovingFactor);
            coords.add(coord);
        }

        return coords;
    }

    final int getMovingFactor(final int x, final int y) {
        return Integer.compare(y, x);
    }
}
