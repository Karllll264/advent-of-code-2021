package aoc.day5;

import lombok.Getter;

@Getter
class Point {
    private final int x;
    private final int y;

    Point(String x, String y) {
        this.x = Integer.parseInt(x);
        this.y = Integer.parseInt(y);
    }
}
